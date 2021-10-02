package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.menu.Menu;
import com.bitcamp.util.Prompt;

public class AuthLoginHandler implements Command{

  List<User> userList;
  static User loginUser;
  static int useAccessLevel = Menu.ACCESS_LOGOUT;
  public static User getLoginUser() {
    return loginUser;
  }
  public static int getUseAccessLevel() {
    return useAccessLevel;
  }

  public AuthLoginHandler(List<User> userList) {
    this.userList = userList;
  }

  public void execute(CommandRequest request) {
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일 > ");
    String password = Prompt.inputString("암호 > ");

    if(email.equals("root@test.com") && password.equals("0000")) {
      loginUser = userList.get(0);
      useAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
      System.out.println("제주정승🍊 환영합니다!");
      return;
    }

    User user = findByEmailPassword(email, password);

    if (user == null) {
      System.out.println("이메일 또는 암호가 일치하지 않음!");
    } else {
      System.out.println("제주옵서예🍊!");
      System.out.printf("%s님 환영합니다!\n", user.getUserNickName());
      useAccessLevel = Menu.ACCESS_GENERAL;
    }

    loginUser = user;
  }

  private User findByEmailPassword(String email, String password) {
    for (User user : userList) {
      if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }
}
