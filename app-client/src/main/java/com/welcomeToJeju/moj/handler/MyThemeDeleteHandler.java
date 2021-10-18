package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class MyThemeDeleteHandler implements Command {

	ThemeDao themeDao;
	
  public MyThemeDeleteHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }

	public void execute(CommandRequest request) throws Exception{
		System.out.println("[테마 삭제하기]");

		String input = Prompt.inputString("삭제하기(y/N) > ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("테마 삭제 취소!");
			return;
		}
		
		Theme theme = (Theme) request.getAttribute("theme");
		String deleteThemeTitle = themeDao.delete(theme.getTitle());
		
		System.out.printf("%s테마 삭제 완료!",deleteThemeTitle);
		System.out.println();
	}

}
