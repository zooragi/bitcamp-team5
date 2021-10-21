package com.welcomeToJeju.moj.handler;

import java.util.Collection;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

public class LikedUserListHandler implements Command {

  UserDao userDao;

  public LikedUserListHandler(UserDao userDao) {
    this.userDao =userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아하는 유저 목록보기]");

    User loginUser = AuthLoginHandler.getLoginUser();

    Collection<User> likedUserList = userDao.likedUserFindAll(loginUser.getNo());
    if (likedUserList.isEmpty()) {
      System.out.println("좋아하는 유저 없음!");
      return;
    }

    int index = 1;
    for (User user : likedUserList ) {
      System.out.printf("<%d> 닉네임 > %s \n", index++, user.getNickname());
    }
  }

}

