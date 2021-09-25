package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapDetailHandler extends AbstractMyMapHandler {

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 상세보기]");
    int index = 1;
    String input = Prompt.inputString("테마 이름을 입력하세요!");
    Theme searchedTheme = findByTitle(input);
    if(searchedTheme == null) {
      System.out.println("해당 이름의 테마가 없습니다.");
      return;
    }

    System.out.printf("테마 제목 : %s\n", searchedTheme.getTitle());
    System.out.printf("카테고리 : %s\n" , searchedTheme.getCategory());
    System.out.printf("해시 태그 : %s\n", searchedTheme.getHashtags().toString());
    System.out.printf("조회수 : %d\n", searchedTheme.getViewCount());
    System.out.println();

    for (Place placeList : searchedTheme.getPlaceList()) {
      System.out.printf("(%d)\n", index++);
      System.out.printf("장소 이름 > %s\n", placeList.getStoreName());
      System.out.printf("장소 주소 > %s\n", placeList.getAddressName());
      System.out.printf("위도 > %s\n", placeList.getxCoord());
      System.out.printf("경도 > %s\n", placeList.getyCoord());
      System.out.printf("장소 후기 > %s\n", placeList.getComment().toString());
      System.out.println();
    }

    User user = AuthLoginHandler.getLoginUser();
    if(!user.getNickName().equals(searchedTheme.getUserName()) && 
        user.getEmail().equals("root@test.com")) {
      return;
    }

    request.setAttrubute("searchedTheme",searchedTheme);
    while(true) {
      input = Prompt.inputString("장소 추가(A), 장소 삭제(D), 이전메뉴(0)");
      switch (input) {
        case "a" :
        case "A" :
          request.getRequestDispatcher("/place/add").forword(request);
          return;

        case "D" :
        case "d" :
          request.getRequestDispatcher("/place/delete").forword(request);
          return;
        case "0" :
          return;
        default :
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}
