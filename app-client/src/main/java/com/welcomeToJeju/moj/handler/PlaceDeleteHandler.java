package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class PlaceDeleteHandler implements Command{

	ThemeDao themeDao;
	PlaceDao placeDao;
  	
  public PlaceDeleteHandler(ThemeDao themeDao,PlaceDao placeDao) {
  	this.themeDao = themeDao;
  	this.placeDao = placeDao;
  }
  
  @Override
  public void execute(CommandRequest request) throws Exception {
    while (true) {
      System.out.println("[장소 삭제하기]");

      Theme theme = (Theme) request.getAttribute("theme");
      theme = themeDao.findByTitle(theme.getTitle());
      
      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        return;
      }
      ArrayList<Place> list = (ArrayList<Place>) placeDao.findByThemeNo(theme.getNo());
      
      Place place = PlaceHandlerHelper.promptPlace(list);
      
      String input = Prompt.inputString("삭제하기(y/N) > ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("장소 삭제하기 취소!");
        System.out.println();
        return;
      }

      placeDao.delete(place);
      
      System.out.printf("삭제하기 완료!\n");
      return;
    }
  }
}