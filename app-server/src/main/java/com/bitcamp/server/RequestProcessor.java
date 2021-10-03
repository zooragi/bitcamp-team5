package com.bitcamp.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.bitcamp.goodplace.Table.UserTable;
import com.google.gson.Gson;

public class RequestProcessor {
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	UserTable userTable = new UserTable();
	
	public RequestProcessor(Socket socket) throws Exception{
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void service() throws Exception{
		while(true) {
			String command = in.readLine();
			
			if(command.equals("quit")) {
				in.readLine();
				out.println("success");
				out.println();
				out.flush();
				break;
			}
			else if(command.startsWith("user."))	{
				Request request = new Request(command,in.readLine());
				Response response = new Response();
				userTable.execute(request, response);
				
				out.println(response.getStatus());
				if(response.getValue() != null) {
					out.println(new Gson().toJson(response.getValue()));
				} else {
					out.println();
				}
				
				out.flush();
			} else if (command.startsWith("get.")) {
				Request request = new Request(command,in.readLine());
				Response response = new Response();
				userTable.execute(request, response);
				
				out.println(response.getStatus());
				if(response.getValue() != null) {
					out.println(new Gson().toJson(response.getValue()));
				} else {
					out.println();
				}
				
				out.flush();
				
			}else {
        in.readLine(); // 클라이언트가 보낸 문자열을 읽어서 버린다.
        out.println("success");
        out.println(command);
        out.flush();
      }
		}	
		
	}

	public void close() {
		try { in.close();} catch(Exception e) {}
		try { out.close();} catch(Exception e) {}
		try { socket.close();} catch(Exception e) {}
	}
}
