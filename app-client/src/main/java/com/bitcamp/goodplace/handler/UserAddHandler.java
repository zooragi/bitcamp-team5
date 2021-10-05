package com.bitcamp.goodplace.handler;

import java.sql.Date;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserAddHandler implements Command{
	UserDao userDao;
//	List<User> userList; 
	
  public UserAddHandler(UserDao userDao) {
  	this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception{

    System.out.println("[회원 가입하기]");

    User user = new User();
//    int userNo = 0;

    user.setEmail(Prompt.inputString("이메일 > "));
    user.setNickName(Prompt.inputString("닉네임 > "));
    user.setPassword(Prompt.inputString("암호 > "));
//    user.setRegisteredDate(new Date(System.currentTimeMillis()));
//    userNo = userList.get(userList.size()-1).getNo();
//    user.setNo(++userNo);
    
    userDao.insert(user);
  
    System.out.println("회원 등록 완료");
  }

}
