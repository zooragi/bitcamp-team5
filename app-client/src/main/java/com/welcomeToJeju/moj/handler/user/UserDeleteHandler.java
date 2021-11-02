package com.welcomeToJeju.moj.handler.user;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class UserDeleteHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public UserDeleteHandler(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[탈퇴하기]");

    User user = (User) request.getAttribute("loginUser");

    String input = Prompt.inputString("탈퇴하기(y/N) > ");

    if (input.equalsIgnoreCase("n") | input.length() == 0) {
      System.out.println("탈퇴하기 취소!");
      return;
    }

    try {
      // 에러
      userDao.delete(user.getNo());
      userDao.deleteAllLikedUser(user.getNo());
      sqlSession.commit();
      System.out.println("탈퇴하기 성공!");

      AuthLoginHandler.loginUser = null;
      AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("탈퇴하기 실패!");
    }
  }


}
