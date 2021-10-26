package com.welcomeToJeju.moj.handler;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserDeleteHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;
  public UserDeleteHandler(UserDao userDao,SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[회원 삭제하기]");

    int no = Prompt.inputInt("번호 > ");

    String input = Prompt.inputString("삭제하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제 취소!");
      return;
    }

    userDao.delete(no);
    userDao.userLikedUserAllDelete(no);
    sqlSession.commit();
    System.out.println("회원 삭제 완료!");
  }
}
