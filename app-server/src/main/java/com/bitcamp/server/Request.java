package com.bitcamp.server;

import java.util.Map;

import com.google.gson.Gson;

public class Request {
	String command;
	String jsonData;
	Map<String,String> params;
	
	public Request(String command, String jsonData) {
		this.command = command;
		this.jsonData = jsonData;
	}
	
	public String getCommand() {
		return command;
	}
	
	public <T> T getObject(Class<T> type) {
		return new Gson().fromJson(jsonData, type);
	}

}
