package com.welcomeToJeju.moj.handler;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class LikedUserDeleteHandler implements Command {

  UserDao userDao;
  SqlSession sqlSession;
  public LikedUserDeleteHandler(UserDao userDao,SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }


	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[좋아하는 유저 삭제하기]");

		String input = Prompt.inputString("삭제할 유저의 닉네임 > ");
		if (input.length() == 0) {
			System.out.println("좋아요 삭제 취소!");
			return;
		}

		User likedUser = userDao.findByName(input);

		if (likedUser == null) {
			System.out.println("등록된 유저 없음!");
			return;
		}

    if(likedUser.getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("본인은 삭제 불가!");
      return;
    }
		HashMap<String,Integer> params = new HashMap<>();
		params.put("likedUserNo", likedUser.getNo());
		params.put("loginUserNo", AuthLoginHandler.getLoginUser().getNo());
		userDao.deleteLikedUser(params);

    System.out.println("좋아하는 유저 삭제 완료!");
	}

}
