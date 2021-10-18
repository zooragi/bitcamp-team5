package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedUserDeleteHandler implements Command {

  UserDao userDao;
  UserPrompt userPrompt;
  
  public LikedUserDeleteHandler(UserDao userDao,UserPrompt userPrompt) {
    this.userDao = userDao;
    this.userPrompt = userPrompt;
  }


	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[좋아하는 유저 삭제하기]");

		String input = Prompt.inputString("삭제할 유저의 닉네임 > ");
		if (input.length() == 0) {
			System.out.println("좋아요 삭제 취소!");
			return;
		}

		User likedUser = userDao.search(input);

		if (likedUser == null) {
			System.out.println("등록된 유저 없음!");
			return;
		}

    if(likedUser.getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("본인은 삭제 불가!");
      return;
    }
    
    userDao.userLikedUserDelete(likedUser.getNo(), AuthLoginHandler.getLoginUser().getNo());

    System.out.println("좋아하는 유저 삭제 완료!");
	}

}
