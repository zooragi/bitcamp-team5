package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class MyThemeAddHandler implements Command {

	ThemeDao themeDao;
	
  public MyThemeAddHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }

	public void execute(CommandRequest request) throws Exception {
		Theme theme = new Theme();
		System.out.println();
		System.out.println("[나의 테마 등록하기]");

		ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();

		theme.setTitle(Prompt.inputString("테마 이름(취소 : 엔터) > "));

		if (theme.getTitle().equals("")) {
			System.out.println("나의 테마 등록 취소!");
			return;
		}

		int uniqueNumber;
		loop: while (true) {
			uniqueNumber = Prompt.inputInt("고유 번호(취소 : 엔터) > ");
			for (Theme t : themeList) {
				if (t.getNo() == uniqueNumber) {
					System.out.println("존재하는 번호입니다. 다시 입력 하시오.");
					continue loop;
				}
			}
			break;
		}
		theme.setNo(uniqueNumber);

		int categoryNum;
		List<String> categories = new ArrayList<>();
		categories.add("식당");
		categories.add("카페");
		categories.add("관광명소");
		categories.add("기타");
		while (true) {
			int index = 1;
			for (String category : categories) {
				System.out.printf("%d. %s ", index++, category);
			}
			System.out.println();
			categoryNum = Prompt.inputInt("카테고리 번호 > ");
			if (categoryNum > categories.size() || categoryNum < 0) {
				System.out.println("잘못된 번호!");
				continue;
			}
			System.out.println();
			break;
		}
		theme.setCategory(categories.get(categoryNum - 1));

		while (true) {
			String input = Prompt.inputString("해시 태그(완료: 엔터) > ");
			if (input.length() == 0)
				break;

			theme.getHashtags().add(input);
		}

		String publicOption = Prompt.inputString("공개 설정(Y/n) > ");
		if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
			theme.setPublic(true);
		}
		theme.setThemeOwnerNo(AuthLoginHandler.getLoginUser().getNo());
		themeDao.insert(theme);

	}

}
