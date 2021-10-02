package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserFollowingDeleteHandler implements Command {

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[팔로잉 삭제]");

    String input = Prompt.inputString("삭제할 유저의 닉네임을 입력하세요");
    for( User user : AuthLoginHandler.getLoginUser().getFollowings()) {
      if(user.getNickName().equals(input)) {
        AuthLoginHandler.getLoginUser().getFollowings().remove(user);
        System.out.println("팔로우를 취소하였습니다.");
        break;
      }
    }

  }


}
