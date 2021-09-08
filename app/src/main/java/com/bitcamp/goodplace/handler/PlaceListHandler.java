package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class PlaceListHandler extends AbstractPlaceHandler{

  public void execute() {
    System.out.println("[장소 목록]");

    Theme theme = findByTitle(Prompt.inputString("테마 이름을 입력하세요> "));

    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    int index = 1;

    System.out.printf("[%s]\n", theme.getTitle());
    for (Place placeList : theme.getPlaceList()) {
      System.out.printf("(%d)\n", index++);
      System.out.printf("장소 이름> %s\n", placeList.getStoreName());
      System.out.printf("장소 주소> %s\n", placeList.getAddress());
      System.out.printf("장소 후기> %s\n", placeList.getComment().toString());
      System.out.println();
    }
  }

}
