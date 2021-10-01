package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.context.UserContextListener;
import com.bitcamp.menu.Menu;

public class AuthLogoutHandler implements Command{

	List<UserContextListener> userListeners;
	
	public AuthLogoutHandler(List<UserContextListener> userListeners) {
		this.userListeners = userListeners;
	}
  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.useAccessLevel = Menu.ACCESS_LOGOUT;
    System.out.println("로그아웃 하였습니다.");
  }

}
