package com.bitcamp.goodplace.handler;

import com.bitcamp.menu.Menu;

public class AuthLogoutHandler implements Command{

  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.useAccessLevel = Menu.ACCESS_LOGOUT;
    System.out.println("로그아웃 하였습니다.");
  }

}
