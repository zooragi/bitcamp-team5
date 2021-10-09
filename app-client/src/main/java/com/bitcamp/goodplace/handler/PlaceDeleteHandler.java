package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

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
      theme = themeDao.selectedOne(theme);
      
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