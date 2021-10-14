package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.UserDao;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class LikedUserDeleteHandler implements Command {

	UserDao userDao;

	public LikedUserDeleteHandler(UserDao userDao) {
		this.userDao = userDao;
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
		String loginUser = AuthLoginHandler.getLoginUser().getNickName();

		if (likedUser == null) {
			System.out.println("등록된 유저 없음!");
			return;
		}

		for (String userName : likedUser.getLikedUsers()) {
			if (userName.equals(loginUser)) {
				userDao.userLikedUserDelete(likedUser.getNickName(), loginUser);
				System.out.println("좋아하는 유저 삭제 완료!");
				return;
			}
		}

		System.out.println("좋아하는 유저 없음!");
	}

}
