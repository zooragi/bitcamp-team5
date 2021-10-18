package com.welcomeToJeju.moj.handler;

import java.util.HashMap;
import java.util.Map;

public class CommandRequest {

  Map<String, Object> paraMap = new HashMap<>();

  Map<String, Command> commandMap;
  public CommandRequest(Map<String, Command> commandMap) {
    this.commandMap = commandMap;
  }

  public void setAttribute(String name, Object value) {
    paraMap.put(name, value);
  }

  public Object getAttribute(String name) {
    return paraMap.get(name);
  }

  public RequestDispatcher getRequestDispatcher(String menuId) {
    Command command = commandMap.get(menuId);
    if (command == null) {
      return null;
    }

    return new RequestDispatcher(command);

  }



}

