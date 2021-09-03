package com.bitcamp.goodplace.Handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class PlaceAddHandler extends AbstractPlaceHandler{

  public void execute() {
    System.out.println("[장소 등록]");

    Theme theme = findByTitle(Prompt.inputString("테마 이름을 입력하세요> "));

    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    Place place = new Place();

    place.setStoreName(Prompt.inputString("장소 이름을 입력하세요> "));
    place.setAddress(Prompt.inputString("장소 주소를 입력하세요> "));
    place.getComment().add(Prompt.inputString("장소 후기를 입력하세요> "));

    theme.getPlaceList().add(place);
    System.out.println("장소를 등록하였습니다.");
  }
}
