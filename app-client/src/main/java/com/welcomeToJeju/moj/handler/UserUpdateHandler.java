package com.welcomeToJeju.moj.handler;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class UserUpdateHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;
  public UserUpdateHandler(UserDao userDao,SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[내 정보 수정하기]");

    User user = (User) request.getAttribute("loginUser");

    User temp = new User();
    temp.setNo(user.getNo());
    temp.setEmail(Prompt.inputString("이메일(" + user.getEmail() + ") > "));
    temp.setPassword(Prompt.inputString("암호 > "));
    temp.setNickname(Prompt.inputString("닉네임(" + user.getNickname() + ") > "));

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("수정 취소!");
      return;
    }

    userDao.update(temp);
    sqlSession.commit();
    System.out.println("회원 수정 완료!");
  }
}
