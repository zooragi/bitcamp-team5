package com.bitcamp.goodplace.handler;

import java.util.Collection;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;

public class LikedUserListHandler implements Command {

  UserDao userDao;

  public LikedUserListHandler(UserDao userDao) {
    this.userDao =userDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아하는 유저 목록보기]");

    User loginUser = AuthLoginHandler.getLoginUser();

    Collection<String> likedUserList = userDao.likedUserFindAll(loginUser);
    if (likedUserList.isEmpty()) {
      System.out.println("좋아하는 유저 없음!");
      return;
    }

    int index = 1;
    for (String user : likedUserList ) {
      System.out.printf("<%d> 닉네임 > %s \n", index++, user);
    }
  }

}

