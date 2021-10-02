package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;

public class PlaceListHandler extends AbstractPlaceHandler{

  @Override
  public void execute(CommandRequest request) {
    while (true) {
      System.out.println("[장소 목록보기]");

      Theme theme = (Theme) request.getAttribute("searchedTheme");

      if (theme == null) {
        System.out.println("등록된 테마 없음!");
        System.out.println();
        continue;
      }

      int index = 1;

      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());

      for (Place place : theme.getPlaceList()) {
        System.out.printf("<%d>\n", index++);
        System.out.printf("장소 이름 > %s\n", place.getStoreName());
        System.out.printf("장소 주소 > %s\n", place.getStoreAddress());
        System.out.printf("위도 > %s\n", place.getxCoord());
        System.out.printf("경도 > %s\n", place.getyCoord());
        System.out.printf("장소 후기> %s\n", place.getComments().toString());
        System.out.println();
      }
    }
  }


}