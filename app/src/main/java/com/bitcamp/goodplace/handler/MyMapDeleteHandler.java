package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapDeleteHandler extends AbstractMyMapHandler{

	public void execute() {
		System.out.println("[테마 삭제]");
		String title = Prompt.inputString("테마 이름을 입력하세요> ");

		Theme theme = findByTitle(title);

		if (theme == null) {
			System.out.println("해당 이름의 테마가 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)> ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("테마 삭제를 취소하였습니다.");
			return;
		}

		AuthLoginHandler.getLoginUser().getThemeList().remove(theme);

		System.out.println("테마를 삭제하였습니다.");
	}

	public Theme findByTitle(String themeTitle) {
		for(Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
			if(theme.getTitle().equals(themeTitle)) return theme;
		}
		return null;
	}
}
