package com.welcomeToJeju.moj.handler.user;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;

public class AuthLogoutHandler implements Command {

  List<UserContextListener> userListeners;

  public AuthLogoutHandler(List<UserContextListener> userListeners) {
    this.userListeners = userListeners;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    notifyOnLogout();
  }

  private void notifyOnLogout() {
    HashMap<String,Object> params = new HashMap<>();

    for (UserContextListener listener : userListeners) {
      listener.contextLogout(params);
    }
  }


}
