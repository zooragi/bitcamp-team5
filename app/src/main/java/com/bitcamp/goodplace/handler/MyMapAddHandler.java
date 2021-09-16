package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapAddHandler extends AbstractMyMapHandler{

	public void execute() {
		Theme theme = new Theme();
		ArrayList<String> hashtagList = (ArrayList<String>) theme.getHashtags();
		System.out.println("나의 테마 등록");

		theme.setTitle(Prompt.inputString("테마 이름을 입력하세요> "));
		while (true) {
			String input = Prompt.inputString("해시 태그를 입력하세요(완료: 빈 문자열)> ");
			if (input.length() == 0)
				break;

			hashtagList.add(input);
		}
		if (Prompt.inputString("공개 설정 하시겠습니까?(y/N)> ").equalsIgnoreCase("y")) {
			theme.setPublic(true);
		}
		theme.setUserName(AuthLoginHandler.getLoginUser().getNickName());
		AuthLoginHandler.getLoginUser().getThemeList().add(theme);
	}
}
