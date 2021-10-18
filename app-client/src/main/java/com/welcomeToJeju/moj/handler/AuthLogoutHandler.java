package com.welcomeToJeju.moj.handler;

import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.menu.Menu;

public class AuthLogoutHandler implements Command{

  List<UserContextListener> userListeners;

  public AuthLogoutHandler(List<UserContextListener> userListeners) {
    this.userListeners = userListeners;
  }

  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.useAccessLevel = Menu.ACCESS_LOGOUT;
    notifyOnLogout();

  }

  private void notifyOnLogout() {
    HashMap<String, Object> params = new HashMap<>();

    for (UserContextListener listener : userListeners) {
      listener.contextLogout(params);
    }
  }


}
