package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

public class UserRankHandler implements Command{
	UserPrompt userPrompt;
	
  public UserRankHandler(UserPrompt userPrompt) {
  	this.userPrompt = userPrompt;
  }

  public void execute(CommandRequest request) throws Exception {
    int i = 1;
    System.out.println("[유저 랭킹]");
    for(User user : userPrompt.rank()) {
      System.out.printf("%d위. %s (조회수 : %d)\n",i,user.getNickName(),user.getViewCount());
      i++;
    }
  }


}
