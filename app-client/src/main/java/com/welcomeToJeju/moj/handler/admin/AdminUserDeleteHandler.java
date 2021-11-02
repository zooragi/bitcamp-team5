package com.welcomeToJeju.moj.handler.admin;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AdminUserDeleteHandler implements Command {

  UserDao userDao;
  ThemeDao themeDao;
  SqlSession sqlSession;

  public AdminUserDeleteHandler(UserDao userDao, ThemeDao themeDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
    this.themeDao = themeDao;
  }
  

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[회원 삭제하기]");

    User user = (User) request.getAttribute("user");

    String input = Prompt.inputString("삭제하기(y/N) > ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 삭제하기 취소!");
      return;
    }

    try {
      // 유저와 엮여있는 것들 다 삭제
    	themeDao.deleteAllLikedThemeByUserNo(user.getNo());
      userDao.delete(user.getNo());
      sqlSession.commit();
      System.out.println("회원 삭제하기 성공!");

    } catch (Exception e) {
    	System.out.println(e);
    	System.out.println("회원 삭제하기 실패!");
      sqlSession.rollback();
    }

  }


}
