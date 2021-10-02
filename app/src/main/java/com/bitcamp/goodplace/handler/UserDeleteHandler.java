package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserDeleteHandler extends AbstractUserHandler{

  public UserDeleteHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {

    System.out.println("[회원 삭제하기]");

    int no = Prompt.inputInt("번호 > ");

    User user= findByNo(no);

    if (user == null) {
      System.out.println("등록된 회원 없음!");
      return;
    }

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제 취소!");
      return;
    }

    userList.remove(user);

    System.out.println("회원 삭제 완료!");
  }
}