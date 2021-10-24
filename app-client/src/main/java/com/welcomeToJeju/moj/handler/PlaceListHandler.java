package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

public class PlaceListHandler implements Command {

	PlaceDao placeDao;
	
  public PlaceListHandler(PlaceDao placeDao) {
  	this.placeDao = placeDao;
  }

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[장소 목록보기]");

		Theme theme = (Theme) request.getAttribute("theme");
		ArrayList<Place> list = (ArrayList<Place>) placeDao.findByThemeNo(theme.getNo());
		System.out.println(list);
		System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory().getName(), theme.getTitle());

		int index = 1;
    for (Place place : list) {
      System.out.printf("<%d>\n", index++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
      System.out.printf("장소 주소 > %s\n", place.getStoreAddress());
      System.out.printf("위도 > %s\n", place.getxCoord());
      System.out.printf("경도 > %s\n", place.getyCoord());
      System.out.printf("사진 > %s\n", place.getPhotos().toString());
      System.out.printf("장소 후기> %s\n", place.getComments().toString());
    }

	}

}