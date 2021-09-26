package com.bitcamp.menu;

import java.util.HashMap;

import com.bitcamp.goodplace.handler.Command;
import com.bitcamp.goodplace.handler.CommandRequest;

public class MenuItem extends Menu{
    String menuId;
    HashMap<String, Command> commandMap;
    
    public MenuItem(String title, HashMap<String, Command> commandMap,String menuId) {
      super(title);
      this.menuId = menuId;
      this.commandMap = commandMap;
    }
    public MenuItem(String title, HashMap<String, Command> commandMap, int enableState, String menuId) {
        super(title, enableState);
        this.menuId = menuId;
        this.commandMap = commandMap;
      }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!", menuId);
        e.printStackTrace();
      } 
    }
}
