package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

public class MyThemeListHandler implements Command {

	ThemeDao themeDao;
	
  public MyThemeListHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }
	
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[내 테마 목록보기]");
    int i = 1 ;
    
    ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findByUserNo(AuthLoginHandler.getLoginUser().getNo());
    
    if (themeList.size() == 0) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    
    for (Theme theme : themeList) {
    	if(AuthLoginHandler.getLoginUser().getNo() == theme.getThemeOwnerNo()) {
    		System.out.printf("<%d>\n", i++);
        System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
        System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
        System.out.printf("%s테마\n", theme.isPublic()==1 ? "공개" : "비공개");
        if (theme.isPublic() == 1) {
          System.out.printf("조회수 > %d\n", theme.getViewCount());
        }
        System.out.println();
    	}
      
    }
  }
}
