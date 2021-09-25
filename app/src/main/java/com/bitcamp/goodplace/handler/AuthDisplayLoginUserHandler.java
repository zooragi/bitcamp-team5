package com.bitcamp.goodplace.handler;

public class AuthDisplayLoginUserHandler implements Command{

  public void execute(CommandRequest request) {
    System.out.println("[내정보]");

    if (AuthLoginHandler.loginUser == null) {
      System.out.println("로그인 하지 않았습니다.");
      return;
    }

    System.out.printf("이메일: %s\n", AuthLoginHandler.loginUser.getEmail());
    System.out.printf("닉네임: %s\n", AuthLoginHandler.loginUser.getNickName());
    System.out.printf("등록일: %s\n", AuthLoginHandler.loginUser.getRegisteredDate());
  }
}
