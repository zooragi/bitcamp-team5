package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

public class UserRankHandler implements Command{
	UserDao userDao;
	
  public UserRankHandler(UserDao userDao) {
  	this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {
    int i = 1;
    System.out.println("[유저 랭킹]");
    for(User user : userDao.sortUserByViewCount()) {
      System.out.printf("%d위. %s (조회수 : %d)\n",i,user.getNickname(),user.getViewCount());
      i++;
    }
  }


}
