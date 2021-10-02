package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserEditHandler implements Command {

  public void execute(CommandRequest request) {

    System.out.println("[내 정보 수정하기]");

    User user = (User) request.getAttribute("loginUser");

    String email = Prompt.inputString("이메일(" + user.getEmail() + ") > ");
    String password = Prompt.inputString("암호 > ");
    String nickName = Prompt.inputString("닉네임(" + user.getNickName() + ") > ");

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("수정 취소!");
      return;
    }

    user.setEmail(email);
    user.setPassword(password);
    user.setNickName(nickName);

    System.out.println("회원 수정 완료!");
  }

}