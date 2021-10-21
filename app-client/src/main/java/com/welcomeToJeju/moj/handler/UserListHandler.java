package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

public class UserListHandler implements Command{

	UserDao userDao;
	
	public UserListHandler(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 목록 보기]");
    ArrayList<User> list = (ArrayList<User>) userDao.findAll();
    for (User user : list) {
      System.out.printf("회원 번호 > %s\n", user.getNo());
      System.out.printf("닉네임 > %s\n", user.getNickname());
      System.out.printf("이메일 > %s\n", user.getEmail());
      System.out.printf("가입일 > %s\n", user.getRegisteredDate());
      System.out.println();
    } 
	}
	
}
