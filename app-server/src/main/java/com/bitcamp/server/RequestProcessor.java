package com.bitcamp.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

import com.bitcamp.goodplace.Table.ThemeTable;
import com.bitcamp.goodplace.Table.UserTable;
import com.google.gson.Gson;

public class RequestProcessor implements AutoCloseable {
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	HashMap<String, DataProcessor> dataProcessorMap;

	UserTable userTable = new UserTable();
	ThemeTable themeTable = new ThemeTable();

	public RequestProcessor(Socket socket, HashMap<String, DataProcessor> dataProcessorMap) throws Exception {
		this.socket = socket;
		this.dataProcessorMap = dataProcessorMap;
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	@Override
	public void close() {
		try {
			in.close();
		} catch (Exception e) {
		}
		try {
			out.close();
		} catch (Exception e) {
		}
		try {
			socket.close();
		} catch (Exception e) {
		}
	}

	public void service() throws Exception {
		Set<String> dataProcessorNames = dataProcessorMap.keySet();

		while (true) {
			String command = in.readLine();
			Request request = new Request(command, in.readLine());
			Response response = new Response();
			
			if (command.equals("quit")) {
				out.println("success");
				out.println();
				out.flush();
				break;
			}
			
			DataProcessor dataProcessor = null;
			for (String key : dataProcessorNames) {
				if (command.startsWith(key)) {
					dataProcessor = dataProcessorMap.get(key);
				}
			}

			if (dataProcessor != null) {
				dataProcessor.execute(request, response);
			} else {
				response.setStatus(Response.FAIL);
				response.setValue("해당 명령어를 처리할 수 없습니다.");
			}
			sendResult(response);
		}
		System.out.println("quit");
	}
	
	private void sendResult(Response response) throws Exception{
		out.println(response.status);
		if(response.getValue() != null) {
      out.println(new Gson().toJson(response.getValue()));
		} else {
			out.println();
		}
		out.flush();
	}
}
