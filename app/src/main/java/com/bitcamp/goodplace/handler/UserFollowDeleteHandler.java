package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserFollowDeleteHandler implements Command {

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[팔로우 삭제하기]");

    String input = Prompt.inputString("삭제할 유저의 닉네임 > ");
    for( User user : AuthLoginHandler.getLoginUser().getLikedUsers()) {
      if(user.getUserNickName().equals(input)) {
        AuthLoginHandler.getLoginUser().getLikedUsers().remove(user);
        System.out.println("팔로우 취소 완료!");
        break;
      }
    }

  }


}
