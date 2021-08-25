/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.bitcamp.goodplace;

import com.bitcamp.menu.MenuGroup;

public class App {
  public static void main(String[] args) {
    MenuGroup mg = new MenuGroup("메인 메뉴");

    MenuGroup myMap = new MenuGroup("나만의 지도");
    mg.add(myMap);

    MenuGroup myMap1 = new MenuGroup("나만의 지도1");
    mg.add(myMap1);

    MenuGroup fullThema = new MenuGroup("전체 테마 보기");
    mg.add(fullThema);

    MenuGroup bookmark = new MenuGroup("북마크");
    mg.add(bookmark);

    MenuGroup rank = new MenuGroup("순위");
    mg.add(rank);

    mg.execute();

  }
}
