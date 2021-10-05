package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collection;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.request.RequestAgent;

public class MyThemeListHandler implements Command {

	RequestAgent requestAgent;
	
	public MyThemeListHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}
	
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[테마 목록보기]");
    int i = 1 ;
    
    requestAgent.request("user.theme.list", AuthLoginHandler.getLoginUser().getNickName());
    ArrayList<Theme> themeList = new ArrayList<>(requestAgent.getObjects(Theme.class));
    
    if (themeList.size() == 0) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    for (Theme theme : themeList) {
      System.out.printf("<%d>\n", i++);
      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
      System.out.printf("%s테마\n", theme.isPublic() ? "공개" : "비공개");
      if (theme.isPublic()) {
        System.out.printf("조회수 > %d\n", theme.getViewCount());
      }
      System.out.println();
    }
  }
}
