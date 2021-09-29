package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapUpdateHandler extends AbstractMyMapHandler {

	public MyMapUpdateHandler(List<User> userList) {
		super(userList);
	}

	public void execute(CommandRequest request) {
		System.out.println("[테마 변경]");

		Theme theme = (Theme) request.getAttribute("searchedTheme");

		if (theme == null) {
			System.out.println("해당 이름의 테마가 없습니다.");
			return;
		}

		String newTitle = Prompt.inputString(String.format("제목(%s)? ", theme.getTitle()));
		String category;
		while (true) {
			System.out.println("1.food 2.cafe 3.life 4.culture");
			category = Prompt.inputString(String.format("카테고리(%s)", theme.getCategory()));
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

		System.out.println("테마를 변경하였습니다.");
	}

}
