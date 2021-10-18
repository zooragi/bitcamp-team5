package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedUserAddHandler implements Command {

  UserDao userDao;
  UserPrompt userPrompt;
  
  public LikedUserAddHandler(UserDao userDao,UserPrompt userPrompt) {
    this.userDao = userDao;
    this.userPrompt = userPrompt;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아하는 유저 등록하기]");
    
    String input = Prompt.inputString("좋아요 할 유저의 닉네임(취소 : 엔터) > ");
    if(input.length()==0) {
      System.out.println("좋아요 등록 취소!");
      return;
    }

    User likedUser = userDao.search(input);

    if(likedUser == null) {
      System.out.println("등록된 유저 없음!");
      return;
    }

    if(likedUser.getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("본인은 좋아요 불가!");
      return;
    }
    if(userPrompt.isAlreadyRegisterLikedUser(likedUser, AuthLoginHandler.getLoginUser().getNo())) {
    	System.out.println("이미 등록된 유저!");
    	return;
    }
    
    userDao.userLikedUserInsert(likedUser.getNo(), AuthLoginHandler.getLoginUser().getNo());

    System.out.println("좋아하는 유저 등록 완료!");

  }


}


