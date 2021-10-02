package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.Prompt;

public class PlaceListHandler extends AbstractPlaceHandler{

  public void execute(CommandRequest request) {
    System.out.println("[장소 목록]");

    Theme theme = (Theme) request.getAttribute("searchedTheme");

    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    int index = 1;

    System.out.printf("[%s]\n", theme.getTitle());
    for (Place placeList : theme.getPlaceList()) {
      System.out.printf("(%d)\n", index++);
      System.out.printf("장소 이름> %s\n", placeList.getStoreName());
      System.out.printf("장소 주소> %s\n", placeList.getAddressName());
      System.out.printf("위도 > %s\n", placeList.getxCoord());
      System.out.printf("경도 > %s\n", placeList.getyCoord());
      System.out.printf("장소 후기> %s\n", placeList.getComment().toString());
      System.out.println();
    }
  }

}
