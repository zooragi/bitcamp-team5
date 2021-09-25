package com.bitcamp.goodplace.handler;

public class RequestDispatcher {

  Command command;

  public RequestDispatcher(Command command) {
    this.command = command;
  }

  public void forword(CommandRequest request) throws Exception {
    command.execute(request);
  }


}
