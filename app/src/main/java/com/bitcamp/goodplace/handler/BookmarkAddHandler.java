package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class BookmarkAddHandler extends AbstactBookmarkHandler{

  public BookmarkAddHandler(List<User> userList) {
		super(userList);
	}

public void execute(CommandRequest request) {
    System.out.println("[북마크에 등록]");

    String themeTitle = Prompt.inputString("테마 이름을 입력하세요>");
    
    Theme theme = findByTheme(themeTitle);
    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다. ");
      return;
    }
    
    if(theme.getUserName().equals(AuthLoginHandler.getLoginUser().getNickName())) {
    	System.out.println("자신의 테마를 등록할 수 없습니다.");
    	return;
    }
    
    System.out.println("북마크를 추가했습니다.");
    AuthLoginHandler.getLoginUser().getBookMarks().add(theme);
  }

}
