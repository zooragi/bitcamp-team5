package com.welcomeToJeju.server;

public interface DataProcessor {
  void execute(Request request, Response response) throws Exception;
}
