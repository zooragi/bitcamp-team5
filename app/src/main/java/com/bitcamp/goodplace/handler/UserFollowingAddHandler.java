package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserFollowingAddHandler extends AbstractUserHandler {

  public UserFollowingAddHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {
    System.out.println("[유저 팔로우]");
    while(true) {
      String input = Prompt.inputString("닉네임을 입력하세요(취소 : 빈 문자열) : ");
      if(input.length()==0) return;
      for(User user : userList) {
        if(user.getNickName().equals(input)) {
          if(input.equals(AuthLoginHandler.getLoginUser().getNickName())) {
            System.out.println("본인은 팔로우 할 수 없습니다.");
            return;
          }

          for(User followingUser : AuthLoginHandler.getLoginUser().getFollowings()) {
            if(followingUser.getNickName().equals(input)) {
              System.out.println("이미 팔로우 한 유저입니다.");
              return;
            }
          }

          input = Prompt.inputString(String.format("[%s]님을 팔로우 하시겠습니까? (y/N)",
              user.getNickName()));
          if(input.equalsIgnoreCase("y")) {
            AuthLoginHandler.getLoginUser().getFollowings().add(user);
          }
          return;
        }
      }
      System.out.println("해당 이름의 유저가 없습니다.");
    }

  }


}
