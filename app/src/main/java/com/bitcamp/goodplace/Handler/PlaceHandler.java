package com.bitcamp.goodplace.Handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class PlaceHandler {

  User user;
  public PlaceHandler(User user) {
    this.user = user;

  }

  public void add() {
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

  public void list() {
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

  public void delete() {
    System.out.println("[장소 삭제]");

    Theme theme = findByTitle(Prompt.inputString("테마 이름을 입력하세요> "));

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

  private Theme findByTitle(String themeTitle) {
    for (Theme list : user.getThemeList()) {
      if (list.getTitle().equals(themeTitle)) {
        return list;
      }
    }
    return null;
  }

}
