package com.bitcamp.server;

public interface DataProcessor {
	void execute(Request request, Response response) throws Exception;
}
