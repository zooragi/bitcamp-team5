package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserUnregisterHandler extends AbstractUserHandler{

  public UserUnregisterHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {


    System.out.println("[회원 탈퇴하기]");

    int no = (int) request.setAttribute("no", no);

    User user = findByNo(no);

    String input = Prompt.inputString("회원 탈퇴하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 탈퇴 취소!");
      return;
    }

    userList.remove(user);

    System.out.println("회원 탈퇴 완료!");
  }
}
