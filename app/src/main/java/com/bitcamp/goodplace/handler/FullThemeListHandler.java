package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class FullThemeListHandler implements Command{

	public void execute() {

		System.out.println("[전체 테마 목록]");

		if (AuthLoginHandler.getLoginUser().getThemeList().size() == 0) {
			System.out.println("등록된 테마가 없습니다.");
		}

		int index = 1;
		for (Theme list : AuthLoginHandler.getLoginUser().getThemeList()) {
			System.out.printf("(%d) ", index++);
			System.out.printf("%s ", list.getTitle());
			System.out.printf("%s", list.getHashtags().toString());
			System.out.println();
		}
	}

}
