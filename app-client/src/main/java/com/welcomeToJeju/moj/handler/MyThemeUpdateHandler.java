package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class MyThemeUpdateHandler implements Command {

	ThemeDao themeDao;
	
  public MyThemeUpdateHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 수정하기]");
    
    Theme theme = (Theme) request.getAttribute("theme");
    int categoryNum;

    if (theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    String newTitle = Prompt.inputString("테마 제목 > ");
    List<String> categories = new ArrayList<>();
    categories.add("식당");
    categories.add("카페");
    categories.add("관광명소");
    categories.add("기타");
    
    while (true) {
      int index = 1;
      for(String category : categories) {
        System.out.printf("%d. %s ",index++,category);
      }
      System.out.println();
      categoryNum = Prompt.inputInt("카테고리 번호 > ");
      if(categoryNum > categories.size() || categoryNum < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }
      System.out.println();
      break;
    }

//    List<String> hashtagList = new ArrayList<>();
//
//    while (true) {
//      String input = Prompt.inputString("해시 태그(완료: 엔터) > ");
//      if (input.length() == 0)
//        break;
//      hashtagList.add(input);
//    }

    int isPublic = 0;

    String publicOption = Prompt.inputString("공개 설정(Y/n) > ");
    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
      isPublic = 1;
    }

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 수정 취소!");
      return;
    }

    theme.setTitle(newTitle);
//    theme.setHashtags(hashtagList);
    theme.setCategory(categories.get(categoryNum-1));
    theme.setPublic(isPublic);
    
    themeDao.update(theme);
    
    
    System.out.println();
    System.out.println("테마 수정 완료!");
  }

}
