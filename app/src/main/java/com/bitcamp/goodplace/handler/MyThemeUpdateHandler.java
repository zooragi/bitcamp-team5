package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyThemeUpdateHandler extends AbstractMyMapHandler {

	public MyThemeUpdateHandler(List<User> userList) {
		super(userList);
	}

	public void execute(CommandRequest request) {
		System.out.println("[테마 변경]");

		Theme theme = (Theme) request.getAttribute("searchedTheme");
		int categoryNum;

		if (theme == null) {
			System.out.println("해당 이름의 테마가 없습니다.");
			return;
		}

		String newTitle = Prompt.inputString(String.format("제목(%s)? ", theme.getTitle()));
		List<String> categories = new ArrayList<>();
		categories.add("food");
		categories.add("cafe");
		categories.add("life");
		categories.add("culture");
		while (true) {
			int index = 1;
			for(String category : categories) {
				System.out.printf("%d. %s ",index++,category);
			}
			System.out.println();
			categoryNum = Prompt.inputInt("카테고리 번호를 입력하시오.");
			if(categoryNum > categories.size() || categoryNum < 0) {
				System.out.println("잘못된 번호 입니다.");
				continue;
			}
			System.out.println();
			break;
		}

		List<String> hashtagList = new ArrayList<>();

		while (true) {
			String input = Prompt.inputString("해시 태그(완료: 빈 문자열) : ");
			if (input.length() == 0)
				break;
			hashtagList.add(input);
		}

		if (Prompt.inputString("공개 설정 하시겠습니까?(y/N) : ").equalsIgnoreCase("y")) {
			theme.setPublic(true);
		} else {
			theme.setPublic(false);
		}

		String input = Prompt.inputString("정말 변경하시겠습니까?(y/N)> ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("테마 변경을 취소하였습니다.");
			return;
		}

		theme.setTitle(newTitle);
		theme.setHashtags(hashtagList);
		theme.setCategory(categories.get(categoryNum-1));

		System.out.println("테마를 변경하였습니다.");
	}

}
