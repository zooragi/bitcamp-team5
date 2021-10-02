package com.bitcamp.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RequestAgent implements AutoCloseable{
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	
	String status;
	String jsonData;
	
	public RequestAgent(String ip, int port) throws Exception{
		this.socket = new Socket(ip,port);
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void request(String command, Object value) throws Exception{
		out.println(command);
		
		if(value != null) {
			out.println(new Gson().toJson(value));
		} else {
			out.println();
		}
		out.flush();
		
		status = in.readLine();
		jsonData = in.readLine();
		
	}

	public String getStatus() {
		return status;
	}
	
	public <T> T getObject(Class<T> type) {
		return new Gson().fromJson(jsonData, type);
	}
	
	public <E> Collection<E> getObjects(Class<E> domainType){
		Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
    return new Gson().fromJson(jsonData, type);
	}
	
	@Override
	public void close() throws Exception {
		try { in.close(); } catch (Exception e) {}
		try { out.close(); } catch (Exception e) {}
		try { socket.close(); } catch (Exception e) {}
	}
	
}
