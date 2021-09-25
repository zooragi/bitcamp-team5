package com.bitcamp.goodplace.handler;

public interface Command {
  void execute(CommandRequest request) throws Exception;
}
