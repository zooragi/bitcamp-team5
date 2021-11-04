package com.welcomeToJeju.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.welcomeToJeju.util.Prompt;

// 역할
// - 다른 메뉴를 포함하는 컨테이너 역할을 수행한다.
// 
public class MenuGroup extends Menu {

  static Stack<Menu> breadCrumb = new Stack<>();

  ArrayList<Menu> childs = new ArrayList<>();
  boolean disablePrevMenu;
  String prevMenuTitle = "이전 메뉴";
  MenuFilter menuFilter;

  public MenuGroup(String title) {
    super(title);
  }

  public MenuGroup(String title, int accessScope) {
    super(title, accessScope);
  }

  public MenuGroup(String title, boolean disablePrevMenu) {
    super(title);
    this.disablePrevMenu = disablePrevMenu;
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

  public void setMenuFilter(MenuFilter menuFilter) {
    this.menuFilter = menuFilter;
  }

  // MenuGroup이 포함하는 하위 Menu를 다룰 수 있도록 메서드를 정의한다.
  public void add(Menu child) {
    childs.add(child);
  }

  // 배열에 들어 있는 Menu 객체를 찾아 제거한다.
  public Menu remove(Menu child) {
    if (childs.remove(child)) {
      return child;
    }
    return null;
  }

  @Override
  public void execute() {
    breadCrumb.push(this);
    while (true) {
      System.out.printf("\n[%s]\n", getTitleMenus());
      List<Menu> menuList = new ArrayList<>();
      for (Menu menu : childs) {
        if (menuFilter != null && !menuFilter.accept(menu)) {
          continue;
        }
        menuList.add(menu);
      }

      for (int i = 0; i < menuList.size(); i++) {
        System.out.printf("%d. %s\n", i + 1, menuList.get(i).title);
      }

      if (!disablePrevMenu) {
        System.out.printf("0. %s\n", this.prevMenuTitle);
      }
      try {
        int menuNo = Prompt.inputInt("선택> ");
        if (menuNo == 0 && !disablePrevMenu) {
          breadCrumb.pop();
          return;
        }
        if (menuNo < 0 || menuNo > childs.size()) {
          System.out.println("무효한 메뉴 번호입니다.");
          continue;
        }
        menuList.get(menuNo - 1).execute();
      } catch (Exception e) {
        System.out.println("------------------------------------------");
        System.out.printf("오류 발생: %s\n", e.getClass().getName());
        System.out.println("------------------------------------------");
      }
    }
  }

  public String getTitleMenus() {
    String path = "";
    for (Menu menu : breadCrumb) {
      if (path.length() > 0) {
        path += " / ";
      }
      path += menu.title;
    }
    return path;
  }
}
