package com.welcomeToJeju.moj.handler.admin;

import java.util.Collection;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;

public class AdminUserListHandler implements Command {

  UserDao userDao;

  public AdminUserListHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 목록 보기]");

    Collection<User> userList = userDao.findAll();

    if (userList.size() == 0) {
      System.out.println("회원 없음!");
      return;
    }

    for (User user : userList) {
      System.out.printf("<%d> %s, %s, %s, 🚨 %d\n",
          user.getNo(),
          user.getEmail(),
          user.getNickname(),
          user.getRegisteredDate(),
          user.getWarningCount());
    }
  }


}
