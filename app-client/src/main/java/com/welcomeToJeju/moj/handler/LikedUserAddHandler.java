package com.welcomeToJeju.moj.handler;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class LikedUserAddHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;
  public LikedUserAddHandler(UserDao userDao,SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아하는 유저 등록하기]");
    
    String input = Prompt.inputString("좋아요 할 유저의 닉네임(취소 : 엔터) > ");
    if(input.length()==0) {
      System.out.println("좋아요 등록 취소!");
      return;
    }

    User likedUser = userDao.findByName(input);
    
    if(likedUser == null) {
      System.out.println("등록된 유저 없음!");
      return;
    }
    
    if(likedUser.getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("본인은 좋아요 불가!");
      return;
    }
		HashMap<String,Integer> params = new HashMap<>();
		params.put("likedUserNo", likedUser.getNo());
		params.put("loginUserNo", AuthLoginHandler.getLoginUser().getNo());
    try {
    	userDao.insertLikedUser(params);
    } catch (Exception e) {
    	System.out.println("이미 등록된 유저!");
    	return;
    }
		
		
		sqlSession.commit();
    System.out.println("좋아하는 유저 등록 완료!");

  }


}


