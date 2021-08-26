package com.bitcamp.goodplace.Handler;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class BookmarkHandler {

  User user;

  public BookmarkHandler(User user) {
    this.user = user;
  }

  public void list() {
    System.out.println("북마크 목록");

    //    Object[] list = user.getBookMark().toArray(new Object[0]);

    for (Object user : user.getBookMark().toArray(new Object[0])) {
      System.out.println(user);
    }
  }

  public void delete() {
    System.out.println("북마크 삭제");

    int no = Prompt.inputInt("북마크 번호> ");
    Object user = findByNo(no);

    if (user == null) {
      System.out.println("해당 번호의 북마크가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제 하시겠습니까?(y/N)> ");
    if (input.equalsIgnoreCase("n")) {
      return;
    }
    this.user.getBookMark().remove(user);
  }

  private Object findByNo(int bookmarkNo) {
    //    Object[] arr = user.getBookMark().toArray(new Object[0]);
    for (Object user : user.getBookMark().toArray(new Object[0])) {
      if (((User) user).getName() == bookmarkNo) {
        return user;
      }
    }
    return null;
  }

  public void listPlace() {
    this.list();

  }
}
