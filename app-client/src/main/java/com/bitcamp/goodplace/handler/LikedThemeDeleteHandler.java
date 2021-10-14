package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.Prompt;

public class LikedThemeDeleteHandler implements Command {

	ThemeDao themeDao;

	public LikedThemeDeleteHandler(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

		System.out.println("[좋아요 삭제하기]");

		String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

		if (title.equals("") || title.length() == 0) {
			System.out.println("좋아요 삭제하기 취소!");
			System.out.println();
			return;
		}

		Theme theme = themeDao.search(title);

    while(true) {
    	String input = Prompt.inputString("정말로 삭제 하시겠습니까?(y/N) : ");
    	if(input.equalsIgnoreCase("y")) {
    		break;
    	} else if (input.equalsIgnoreCase("n")) {
    		System.out.println("삭제 취소");
    		return;
    	} else {
    		System.out.println("잘못된 입력입니다. 다시 입력하세요.");
    		continue;
    	}
    }

		themeDao.likedThemeDelete(theme.getTitle(), AuthLoginHandler.getLoginUser().getNickName());
		System.out.println("좋아요 삭제하기 완료!");
	}

}