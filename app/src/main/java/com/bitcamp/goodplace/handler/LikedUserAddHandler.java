package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedUserAddHandler extends AbstractUserHandler {

  public LikedUserAddHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {
    System.out.println("[팔로우 등록하기]");
    while(true) {

      String input = Prompt.inputString("팔로우 할 유저의 닉네임(취소 : 엔터) > ");
      if(input.length()==0) {
        System.out.println("팔로우 등록 취소!");
        return;
      }

      User user = findByUserNickName(input);

      if(user == AuthLoginHandler.getLoginUser()) {
        System.out.println("본인은 팔로우 불가!");
        continue;
      }

      for(User followUser : AuthLoginHandler.getLoginUser().getLikedUsers()) {
        if(followUser.getNickName().equals(input)) {
          System.out.println("이미 팔로우 한 유저!");
          return;
        }
      }

      if(user == null) {
        System.out.println("등록된 유저 없음!");
        continue;
      }

      AuthLoginHandler.getLoginUser().getLikedUsers().add(user);
      System.out.println("팔로우 등록 완료!");
      break;
    }
  }

  private User findByUserNickName(String userNickName) {
    for(User user : userList) {
      if(user.getNickName().equals(userNickName)) {
        return user;
      }
    }
    return null;
  }


}

