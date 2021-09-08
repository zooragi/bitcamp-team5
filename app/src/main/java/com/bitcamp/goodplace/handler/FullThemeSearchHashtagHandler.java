package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class FullThemeSearchHashtagHandler implements Command{
	public void execute() {
		System.out.println("[해시 태그 검색]");

		String input = Prompt.inputString("해시 태그를 입력하세요> ");

		List<Theme> searchedTheme = new ArrayList<>();

		for (Theme themeList : AuthLoginHandler.getLoginUser().getThemeList()) {
			for (String hashtag : themeList.getHashtags()) {
				if (hashtag.contains(input)) {
					searchedTheme.add(themeList);
				}
			}
		}

		if (searchedTheme.size() == 0) {
			System.out.println("해당하는 테마가 없습니다.");
		}

		System.out.printf("[%s]\n", input);
		for (Theme list : searchedTheme) {
			System.out.printf("%s\n", list.getTitle());
		}
	}

}
