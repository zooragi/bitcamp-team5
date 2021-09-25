package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;

public class UserFollowersListHandler extends AbstractUserHandler {

  public UserFollowersListHandler(List<User> userList) {
    super(userList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[팔로잉 목록]");
    if (AuthLoginHandler.getLoginUser().getFollowings().size() == 0) {
      System.out.println("팔로우하는 유저가 없습니다.");
      return;
    }

    int index = 1;
    for (User list : AuthLoginHandler.getLoginUser().getFollowings()) {
      System.out.printf("(%d) 닉네임 : %s \n", index++, list.getNickName());
      System.out.println();
    }
  }

}
