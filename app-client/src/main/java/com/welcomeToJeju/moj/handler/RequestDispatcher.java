package com.welcomeToJeju.moj.handler;

public class RequestDispatcher {

  Command command;

  public RequestDispatcher(Command command) {
    this.command = command;
  }

  public void forward(CommandRequest request) throws Exception {
    command.execute(request);
  }


}
