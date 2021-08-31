package com.bitcamp.goodplace.Handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class FullThemeHandler {
	User user;

	public FullThemeHandler(User user) {
		this.user = user;
	}

	public void list() {

		System.out.println("[전체 테마 목록]");

		if (user.getThemeList().size() == 0) {
			System.out.println("등록된 테마가 없습니다.");
		}

		int index = 1;
		for (Theme list : user.getThemeList()) {
			System.out.printf("(%d) ", index++);
			System.out.printf("%s ", list.getTitle());
			System.out.printf("%s", list.getHashtags().toString());
			System.out.println();
		}
	}

	public void searchHashtag() {
		System.out.println("[해시 태그 검색]");

		String input = Prompt.inputString("해시 태그를 입력하세요> ");

		List<Theme> searchedTheme = new ArrayList<>();

		for (Theme themeList : user.getThemeList()) {
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

	public void searcharea() {
		System.out.println("[지역 검색]");

		String input = Prompt.inputString("지역을 입력하세요> ");

	}

}
