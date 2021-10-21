package com.welcomeToJeju.moj.handler;

import java.sql.Date;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserAddHandler implements Command{
	UserDao userDao;
	
  public UserAddHandler(UserDao userDao) {
  	this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception{

    System.out.println("[회원 가입하기]");

    User user = new User();

    user.setEmail(Prompt.inputString("이메일 > "));
    user.setNickname(Prompt.inputString("닉네임 > "));
    user.setPassword(Prompt.inputString("암호 > "));
    
    userDao.insert(user);
  
    System.out.println("회원 등록 완료");
  }

}
