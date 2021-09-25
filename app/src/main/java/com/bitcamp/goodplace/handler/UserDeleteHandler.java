package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserDeleteHandler extends AbstractUserHandler{

  public UserDeleteHandler(List<User> user) {
    super(user);
  }
  public void execute(CommandRequest request) {
    System.out.println("[회원 삭제]");
    int no = Prompt.inputInt("번호? ");

    User user= findByNo(no);

    if (user == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제를 취소하였습니다.");
      return;
    }

    userList.remove(user);

    System.out.println("회원을 삭제하였습니다.");
  }

}
