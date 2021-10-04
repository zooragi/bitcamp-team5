package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class MyThemeAddHandler implements Command {

  RequestAgent requestAgent;
  
  public MyThemeAddHandler(RequestAgent requestAgent) {
  	this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception{
    Theme theme = new Theme();
    System.out.println("[나의 테마 등록하기]");

    if(AuthLoginHandler.getLoginUser().getThemeList().size() == 0) {
      theme.setNo(1);
    } else {
      int themeNo = AuthLoginHandler.getLoginUser().
          getThemeList().get(AuthLoginHandler.getLoginUser().getThemeList().size()-1).getNo();
      theme.setNo(++themeNo);
    }

    theme.setTitle(Prompt.inputString("테마 이름(취소 : 엔터) > "));

    if(theme.getTitle().equals("")) {
      System.out.println("나의 테마 등록 취소!");
      return;
    }

    int categoryNum;
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
    theme.setCategory(categories.get(categoryNum-1));

    while (true) {
      String input = Prompt.inputString("해시 태그(완료: 엔터) > ");
      if (input.length() == 0)
        break;

      theme.getHashtags().add(input);
    }

    String publicOption = Prompt.inputString("공개 설정(Y/n) > ");
    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("") ) {
      theme.setPublic(true);
    }

    theme.setThemeOwnerName(AuthLoginHandler.getLoginUser().getNickName());
    requestAgent.request("theme.insert", theme);
    
    if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
    	System.out.println("테마 등록 완료!");
    } else {
    	System.out.println("테마 등록 실패!");
    }
    
  }

}
