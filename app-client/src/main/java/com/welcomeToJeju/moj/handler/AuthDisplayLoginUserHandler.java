package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.util.Prompt;

public class AuthDisplayLoginUserHandler implements Command{

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[내정보]");

    if (AuthLoginHandler.loginUser == null) {
      System.out.println("로그인하지 않았습니다!");
      return;
    }

    System.out.printf("이메일 > %s\n", AuthLoginHandler.loginUser.getEmail());
    System.out.printf("닉네임 > %s\n", AuthLoginHandler.loginUser.getNickName());
    System.out.printf("등록일 > %s\n", AuthLoginHandler.loginUser.getRegisteredDate());


    request.setAttribute("loginUser", AuthLoginHandler.loginUser);
    String input = Prompt.inputString("회원 정보 수정(U) / 회원 탈퇴하기(D) > ");
    switch (input) {
      case "U":
      case "u":
        request.getRequestDispatcher("/auth/edit").forward(request);
        return;
      case "D":
      case "d":
        request.getRequestDispatcher("/auth/unregistered").forward(request);
        return;
      default:
        System.out.println("명령어가 올바르지 않습니다!");
    }
  }
}
