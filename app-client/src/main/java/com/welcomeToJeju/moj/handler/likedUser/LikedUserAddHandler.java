package com.welcomeToJeju.moj.handler.likedUser;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class LikedUserAddHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;

  public LikedUserAddHandler(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유저 좋아요 누르기]");

    String input = Prompt.inputString("닉네임(취소 : 엔터) > ");

    if (input.equals("") || input.length() == 0) {
      System.out.println("유저 좋아요 누르기 취소!");
      return;
    }

    User user = userDao.findByNickname(input);

    if(user == null) {
      System.out.println("유저 없음!");
      return;
    }

    if(user.getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("자기를 목록에 더할 수 없음!");
      return;
    }

    //    User loginUser = AuthLoginHandler.getLoginUser();
    //    Collection<User> likedUserList = userDao.findAllLikedUser(loginUser.getNo());
    //
    //    for (User u : likedUserList) {
    //      if (user.getNo() == u.getNo()) {
    //        System.out.println("이미 좋아하는 유저!");
    //        return;
    //      }
    //    }

    try {
      userDao.insertLikedUser(user.getNo(), AuthLoginHandler.getLoginUser().getNo());
      sqlSession.commit();
      System.out.println("유저 좋아요 누르기 성공!");

    } catch (Exception e) {
      System.out.println("이미 좋아하는 유저!");
      return;
    }
  }


}
