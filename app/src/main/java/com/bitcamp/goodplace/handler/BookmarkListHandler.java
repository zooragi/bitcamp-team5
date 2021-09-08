package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class BookmarkListHandler implements Command{

	public void execute() {
		System.out.println("[북마크 목록]");

		int index = 1;
		for (Theme user : AuthLoginHandler.getLoginUser().getBookMarks()) {
			System.out.printf("(%d) ", index++);
			System.out.printf("%s ", user.getTitle());
			System.out.printf("%s", user.getHashtags().toString());
			System.out.println();
		}
	}
}
