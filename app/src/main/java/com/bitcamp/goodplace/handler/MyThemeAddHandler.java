package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyThemeAddHandler extends AbstractMyMapHandler {

	public MyThemeAddHandler(List<User> userList) {
		super(userList);
	}

	public void execute(CommandRequest request) {
		Theme theme = new Theme();
		ArrayList<String> hashtagList = (ArrayList<String>) theme.getHashtags();
		System.out.println("나의 테마 등록");

		theme.setTitle(Prompt.inputString("테마 이름을 입력하세요> "));

		while (true) {
			System.out.println("1.food 2.cafe 3.life 4.culture ");
			String category = Prompt.inputString("카테고리를 선택하세요 >");
			System.out.println();
			// if(category < 0 || category > 8) {
			// System.out.println("유효한 카테고리 번호를 입력하세요오로로롱");
			// continue;
			// }
			switch (category) {
			case "1":
				theme.setCategory("food");
				break;
			case "2":
				theme.setCategory("cafe");
				break;
			case "3":
				theme.setCategory("life");
				break;
			case "4":
				theme.setCategory("culture");
				break;
			default:
				System.out.println("유효한 카테고리 번호를 입력하세요.");
				System.out.println();
				continue;
			}
			break;
		}

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

	// private String selectedCategory (int category) {
	// // while(true) {
	// switch(category) {
	// case 1 : return "food";
	// case 2 : return "cafe";
	// case 3 : return "store";
	// case 4 : return "work";
	// case 5 : return "life";
	// case 6 : return "pet";
	// case 7 : return "culture";
	// default :
	// return null;
	// // }
	// }

}
