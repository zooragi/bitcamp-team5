package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class LikedThemeAddHandler implements Command {

  ThemeDao themeDao;

  public LikedThemeAddHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아요 등록하기]");

    String title = Prompt.inputString("테마 이름(취소 : 엔터) > ");

    if (title.equals("") || title.length() == 0) {
      System.out.println("좋아요 등록하기 취소!");
      System.out.println();
      return;
    }

    Theme theme = themeDao.search(title);

    if (theme == null) {
      System.out.println("등록된 테마 없음!");
      System.out.println();
      return;
    }

    if (theme.getOwner().getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("본인의 테마 좋아요 등록 불가!");
      return;
    }
    
    PlaceHandlerHelper.printPlaceInfo(theme);

    while(true) {
    	String input = Prompt.inputString("정말로 등록 하시겠습니까?(y/N) : ");
    	if(input.equalsIgnoreCase("y")) {
    		break;
    	} else if (input.equalsIgnoreCase("n")) {
    		System.out.println("등록 취소");
    		return;
    	} else {
    		System.out.println("잘못된 입력입니다. 다시 입력하세요.");
    		continue;
    	}
    }

    themeDao.likedThemeInsert(theme.getNo(), AuthLoginHandler.getLoginUser().getNo());


    System.out.println("좋아요 등록 완료!");
  }


}