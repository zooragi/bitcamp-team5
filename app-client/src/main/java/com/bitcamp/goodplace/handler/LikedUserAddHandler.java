package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedUserAddHandler implements Command {

  UserDao userDao;

  public LikedUserAddHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아하는 유저 등록하기]");
    //    while(true) {
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

    for(String userName : likedUser.getLikedUsers() ) {
      if(AuthLoginHandler.getLoginUser().getNickName().equals(userName)) {
        System.out.println("이미 좋아요 한 유저!");
        return;
      }
    }

    String loginUser = AuthLoginHandler.getLoginUser().getNickName();

    userDao.userLikedUserInsert(likedUser.getNickName(), loginUser);

    System.out.println("좋아하는 유저 등록 완료!");

    //    requestAgent.request("user.likedUser.insert", parameter);
    //    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
    //      System.out.println("팔로우 등록 추가!");
    //    } else {
    //      System.out.println("팔로우 등록 불가!");
    //    }
    //  }
  }


}


