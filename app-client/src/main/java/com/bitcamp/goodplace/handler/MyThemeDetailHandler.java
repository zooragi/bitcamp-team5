package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyThemeDetailHandler implements Command {
  Map<String, Map<String, String>> controlMenu = new HashMap<>();

  public MyThemeDetailHandler() {
    addPlaceDetailMapValue();
    addThemeDetailMapValue();
    controlMenu.put("이전 메뉴",null);
  }

  @SuppressWarnings("unlikely-arg-type")
  @Override
  public void execute(CommandRequest request) throws Exception {
    Theme searchedTheme;

    System.out.println("[테마 상세보기]");
    System.out.println();

    showThemeList();
    searchedTheme = chooseTheme();

    User user = AuthLoginHandler.getLoginUser();
    if (!user.getNickName().equals(searchedTheme.getThemeOwnerName()) && user.getEmail().equals("root@test.com")) {
      return;
    }

    request.setAttribute("searchedTheme", searchedTheme);

    while (true) {
      System.out.println();

      ArrayList<String> controlMenuListOfKeys = new ArrayList<>(controlMenu.keySet());
      Collections.swap(controlMenuListOfKeys, controlMenuListOfKeys.indexOf("이전 메뉴"), controlMenuListOfKeys.size()-1);
      showMenuList(controlMenuListOfKeys);
      int selectedNum = chooseMenu(controlMenuListOfKeys.size());

      Map<String, String> detailMenu = controlMenu.get(controlMenuListOfKeys.get(selectedNum - 1));

      if(detailMenu == null) return;

      ArrayList<String> detailMenuListOfKeys = new ArrayList<>(detailMenu.keySet());
      showMenuList(detailMenuListOfKeys);
      selectedNum = chooseMenu(detailMenuListOfKeys.size());
      if(detailMenu.get(detailMenuListOfKeys.get(selectedNum-1)).equals("0")) return;

      request.getRequestDispatcher(detailMenu.get(detailMenuListOfKeys.get(selectedNum-1))).forward(request);
    }

  }

  private void showThemeList() {
    for (Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
      System.out.printf("<%d>\n", theme.getNo());
      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
      System.out.printf("%s테마\n", theme.isPublic() ? "공개" : "비공개");
      if (theme.isPublic()) {
        System.out.printf("조회수 > %d\n", theme.getViewCount());
      }
      System.out.println();
    }
  }

  private Theme chooseTheme() {
    while (true) {
      try {
        int inputNum = Prompt.inputInt("번호 입력 > ");
        if (inputNum > AuthLoginHandler.getLoginUser().getThemeList().size() || inputNum < 0) {
          System.out.println("잘못된 번호!");
          continue;
        }
        return AuthLoginHandler.getLoginUser().getThemeList().get(inputNum - 1);
      } catch (Exception e) {
        System.out.println("------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        System.out.println("------------------------------------------");
      }

    }
  }

  private void showMenuList(List<String> list) {
  	int i = 1;
    for (String key : list) {
      System.out.printf("%d. %s\n", i++, key);
    }
  }
  private int chooseMenu(int size) {
    int selectedNum;
    while(true) {
      try {
        selectedNum = Prompt.inputInt("선택> ");
        if(selectedNum > size || selectedNum < 0) {
          System.out.println("잘못된 번호!");
          continue;
        }
        return selectedNum;
      } catch(Exception e) {
        System.out.println("------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        System.out.println("------------------------------------------");
      }
    }
  }

  private void addPlaceDetailMapValue() {
    Map<String, String> placeMenu = new HashMap<>();
    placeMenu.put("장소 등록하기", "/place/add");
    placeMenu.put("장소 목록보기", "/place/list");
    placeMenu.put("장소 삭제하기", "/place/delete");
    placeMenu.put("이전 메뉴", "0");
    controlMenu.put("지도관리", placeMenu);
  }

  private void addThemeDetailMapValue() {
    Map<String, String> themeMenu = new HashMap<>();
    themeMenu.put("테마 수정하기", "/myMap/update");
    themeMenu.put("테마 삭제하기", "/myMap/delete");
    themeMenu.put("이전 메뉴", "0");
    controlMenu.put("테마관리", themeMenu);
  }

}