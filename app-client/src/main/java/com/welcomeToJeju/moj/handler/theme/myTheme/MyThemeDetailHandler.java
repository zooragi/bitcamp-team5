package com.welcomeToJeju.moj.handler.theme.myTheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class MyThemeDetailHandler implements Command {
  Map<String, Map<String, String>> controlMenu = new HashMap<>();
  ThemeDao themeDao;
  ArrayList<Theme> myThemeList = new ArrayList<>();

  public MyThemeDetailHandler(ThemeDao themeDao) {
    addPlaceDetailMapValue();
    addThemeDetailMapValue();
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Theme searchedTheme;

    ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findByUserNo(AuthLoginHandler.getLoginUser().getNo());
    findMyThemeList(themeList);

    System.out.println("[테마 상세보기]");
    System.out.println();

    showThemeList(myThemeList);
    searchedTheme = chooseTheme(myThemeList);
    if(searchedTheme == null) return;
    User user = AuthLoginHandler.getLoginUser();
    if (!(user.getNo() == searchedTheme.getOwner().getNo()) && user.getEmail().equals("root@test.com")) {
      return;
    }

    request.setAttribute("theme", searchedTheme);

    while (true) {
      System.out.println();

      ArrayList<String> controlMenuListOfKeys = new ArrayList<>(controlMenu.keySet());
      showMenuList(controlMenuListOfKeys);
      int selectedNum = chooseMenu(controlMenuListOfKeys.size());
      if(selectedNum == 0) return;

      Map<String, String> detailMenu = controlMenu.get(controlMenuListOfKeys.get(selectedNum - 1));

      if(detailMenu == null) return;

      ArrayList<String> detailMenuListOfKeys = new ArrayList<>(detailMenu.keySet());
      showMenuList(detailMenuListOfKeys);
      selectedNum = chooseMenu(detailMenuListOfKeys.size());
      if(selectedNum == 0) return;

      request.getRequestDispatcher(detailMenu.get(detailMenuListOfKeys.get(selectedNum-1))).forward(request);
    }

  }

  private void findMyThemeList(ArrayList<Theme> themeList) {
    myThemeList.clear();
    for(Theme theme : themeList) {
      if(theme.getOwner().getNo() == (AuthLoginHandler.getLoginUser().getNo())) {
        myThemeList.add(theme);
      }
    }

  }

  private void showThemeList(ArrayList<Theme> themeList) {
    int i = 1;
    for (Theme theme : themeList) {
      System.out.printf("<%d>\n", i++);
      System.out.printf("테마 제목 > [%s] %s\n", theme.getCategory().getName(), theme.getTitle());
      System.out.println();
    }
  }

  private Theme chooseTheme(ArrayList<Theme> themeList) {
    while (true) {
      try {
        int inputNum = Prompt.inputInt("번호 입력(0번 취소) > ");
        if(inputNum == 0) return null;
        if (inputNum > themeList.size() || inputNum < 0) {
          System.out.println("잘못된 번호!");
          continue;
        }
        return themeList.get(inputNum - 1);
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
    System.out.println("0. 이전메뉴");
    System.out.println();
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
    controlMenu.put("지도관리", placeMenu);
  }

  private void addThemeDetailMapValue() {
    Map<String, String> themeMenu = new HashMap<>();
    themeMenu.put("테마 수정하기", "/myTheme/update");
    themeMenu.put("테마 삭제하기", "/myTheme/delete");
    controlMenu.put("테마관리", themeMenu);
  }

}