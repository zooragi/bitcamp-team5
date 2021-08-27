package com.bitcamp.goodplace;

import com.bitcamp.goodplace.Handler.BookmarkHandler;
import com.bitcamp.goodplace.Handler.MyMapHandler;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.util.Prompt;

public class App {
  public static void main(String[] args) {
    User user = new User();
    MyMapHandler myMapHandler = new MyMapHandler(user);
    BookmarkHandler bookmarkHandler = new BookmarkHandler(user);

    MenuGroup mg = new MenuGroup("메인 메뉴");
    mg.setPrevMenuTitle("종료");

    MenuGroup myMap = new MenuGroup("나만의 지도");
    myMap.add(new Menu("테마 등록") {
      @Override
      public void execute() {
        myMapHandler.add();
      }

    });
    myMap.add(new Menu("테마 목록") {
      @Override
      public void execute() {
        myMapHandler.list();
      }

    });

    myMap.add(new Menu("테마 수정") {
      @Override
      public void execute() {
        myMapHandler.update();
      }

    });

    myMap.add(new Menu("테마 삭제") {
      @Override
      public void execute() {
        myMapHandler.delete();
      }

    });

    mg.add(myMap);

    MenuGroup fullTheme = new MenuGroup("전체 테마 보기");

    fullTheme.add(new Menu("전체테마 목록조회") {
      @Override
      public void execute() {
        bookmarkHandler.list();
      }
    });

    mg.add(fullTheme);


    MenuGroup bookmark = new MenuGroup("북마크");

    bookmark.add(new Menu("북마크 등록") {
      @Override
      public void execute() {
        bookmarkHandler.add();
      }
    });

    bookmark.add(new Menu("북마크 목록") {
      @Override
      public void execute() {
        bookmarkHandler.list();
      }
    }); 


    bookmark.add(new Menu("북마크 삭제") {
      @Override
      public void execute() {
        bookmarkHandler.delete();
      }
    });


    MenuGroup bookmarkDetail = new MenuGroup("상세보기");
    bookmark.add(bookmarkDetail);

    bookmarkDetail.add(new MenuGroup("장소 목록 조회") {
      @Override
      public void execute() {
        bookmarkHandler.list();
      }
    });

    bookmarkDetail.add(new MenuGroup("장소 검색") {
      @Override
      public void execute() {
        bookmarkHandler.list();
      }
    });

    bookmarkDetail.add(new MenuGroup("장소 상세 보기") {
      @Override
      public void execute() {
        bookmarkHandler.list();
      }
    });

    mg.add(bookmark);

    MenuGroup rank = new MenuGroup("순위");
    mg.add(rank);

    mg.execute();

    Prompt.close();
  }
}