package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserUpdateHandler implements Command {

  UserDao userDao;

  public UserUpdateHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[내 정보 수정하기]");

    User user = (User) request.getAttribute("loginUser");

    User temp = new User();

    temp.setEmail(Prompt.inputString("이메일(" + user.getEmail() + ") > "));
    temp.setPassword(Prompt.inputString("암호 > "));
    temp.setNickName(Prompt.inputString("닉네임(" + user.getNickName() + ") > "));

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("수정 취소!");
      return;
    }

    userDao.update(temp);
    System.out.println("회원 수정 완료!");
  }
}
