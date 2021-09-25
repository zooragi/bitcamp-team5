package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserDetailHandler extends AbstractUserHandler{
  public UserDetailHandler(List<User> user) {
    super(user);
  }

  public void execute(CommandRequest request) {
    System.out.println("[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");

    User user = findByNo(no);

    if (user == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("닉네임: %s\n", user.getNickName());
    System.out.printf("등록일: %s\n", user.getRegisteredDate());




  }

}
