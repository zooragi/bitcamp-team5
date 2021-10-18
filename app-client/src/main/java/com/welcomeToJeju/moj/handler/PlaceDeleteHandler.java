package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class PlaceDeleteHandler implements Command{

	ThemeDao themeDao;
	
  public PlaceDeleteHandler(ThemeDao themeDao) {
  	this.themeDao = themeDao;
  }
  
  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      System.out.println("[장소 삭제하기]");

      Theme theme = (Theme) request.getAttribute("theme");
      theme = themeDao.search(theme.getTitle());
      
      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        return;
      }
      
      Place place = PlaceHandlerHelper.promptPlace(theme);
      
      String input = Prompt.inputString("삭제하기(y/N) > ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("장소 삭제하기 취소!");
        System.out.println();
        return;
      }

      String deletedPlace = themeDao.placeDelete(place);
      
      System.out.printf("%s 삭제하기 완료!\n",deletedPlace);
      return;
    }
  }
}