package com.welcomeToJeju.moj.handler.ranking;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;

public class UserRankingHandler implements Command {
  UserDao userDao;

  public UserRankingHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유저 순위 보기]");

    int no = 1;
    for(User user : userDao.userRankingByViewCount()) {
      System.out.printf("%d위 > %s (조회수 : %d)\n", no++, user.getNickname(), user.getViewCount());
    }
  }


}
