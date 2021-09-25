package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.Prompt;

public class PlaceDeleteHandler extends AbstractPlaceHandler{

  public void execute(CommandRequest request) {
    System.out.println("[장소 삭제]");

    Theme theme = (Theme) request.getAttribute("searchedTheme");

    if (theme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    String storeName = Prompt.inputString("장소 이름을 입력하세요> ");
    Place place = null;
    for (Place list : theme.getPlaceList()) {
      if (list.getStoreName().equals(storeName)) {
        place = list;
        break;
      }
    }

    if (place == null) {
      System.out.println("해당 이름의 장소가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N)> ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("장소 삭제를 취소하였습니다.");
      return;
    }

    theme.getPlaceList().remove(place);
    System.out.println("장소를 삭제하였습니다.");
  }
}
