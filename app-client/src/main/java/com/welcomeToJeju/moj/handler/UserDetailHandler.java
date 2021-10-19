package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserDetailHandler implements Command {

	UserDao userDao;
	
  public UserDetailHandler(UserDao userDao) {
  	this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 상세보기]"); // 관리자용

    int no = Prompt.inputInt("번호 > ");

    User user = userDao.findByNo(no);

    if (user == null) {
      System.out.println("등록된 회원 없음!");
      return;
    }
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("닉네임: %s\n", user.getNickName());
    System.out.printf("등록일: %s\n", user.getRegisteredDate());
  }
}