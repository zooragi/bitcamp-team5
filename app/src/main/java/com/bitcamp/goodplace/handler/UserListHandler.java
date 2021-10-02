package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;

public class UserListHandler extends AbstractUserHandler{
  public UserListHandler(List<User> userList) {
    super(userList);
  }
  public void execute(CommandRequest request) {
    System.out.println("[회원 목록 보기]");

    for (User user : userList) {
      System.out.printf("회원 번호 > %s\n", user.getNo());
      System.out.printf("회원 이름 > %s\n", user.getNickName());
      System.out.printf("가입일 > %s\n", user.getRegisteredDate());
      System.out.printf("팔로우 > %d\n", user.getLikedUsers().size());
      System.out.println();
    } 
  }
}