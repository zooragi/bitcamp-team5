package com.welcomeToJeju.moj.handler.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AuthLoginHandler implements Command {


  static User loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT;

  User user;
  UserDao userDao;

  List<UserContextListener> userListeners = new ArrayList<>();

  public static User getLoginUser() {
    return loginUser;
  }
  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLoginHandler(UserDao userDao, List<UserContextListener> userListeners) {
    this.userDao = userDao;
    this.userListeners = userListeners;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일 > ");
    String password = Prompt.inputString("비밀번호 > ");

    user = userDao.findByEmailAndPassword(email, password);

    if (user == null) {
      System.out.println("로그인 실패!");
      return;
    }

    loginUser = user;
    userAccessLevel = Menu.ACCESS_GENERAL;

    if(email.equals("root@test.com") && password.equals("0000")) {
      loginUser = user;
      userAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
      return;
    }

    notifyOnLogin();
  }

  private void notifyOnLogin() {
    HashMap<String,Object> params = new HashMap<>();

    params.put("currentUser", user);

    for (UserContextListener listener : userListeners) {
      listener.contextLogin(params);
    }
  }


}
