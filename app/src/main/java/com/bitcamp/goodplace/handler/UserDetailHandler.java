package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserDetailHandler extends AbstractUserHandler {

  public UserDetailHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {

    System.out.println("[회원 상세보기]"); // 관리자용

    int no = Prompt.inputInt("번호 > ");

    User user = findByNo(no);

    if (user == null) {
      System.out.println("등록된 회원 없음!");
      return;
    }
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("닉네임: %s\n", user.getNickName());
    System.out.printf("등록일: %s\n", user.getRegisteredDate());
  }
}