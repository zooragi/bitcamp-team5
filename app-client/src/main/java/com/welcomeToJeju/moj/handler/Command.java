package com.welcomeToJeju.moj.handler;

public interface Command {
  void execute(CommandRequest request) throws Exception;
}
