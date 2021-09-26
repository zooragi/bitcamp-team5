package com.bitcamp.goodplace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.goodplace.handler.AuthDisplayLoginUserHandler;
import com.bitcamp.goodplace.handler.AuthLoginHandler;
import com.bitcamp.goodplace.handler.AuthLogoutHandler;
import com.bitcamp.goodplace.handler.BookmarkAddHandler;
import com.bitcamp.goodplace.handler.BookmarkDeleteHandler;
import com.bitcamp.goodplace.handler.BookmarkListHandler;
import com.bitcamp.goodplace.handler.Command;
import com.bitcamp.goodplace.handler.FullThemeListHandler;
import com.bitcamp.goodplace.handler.MyMapAddHandler;
import com.bitcamp.goodplace.handler.MyMapDeleteHandler;
import com.bitcamp.goodplace.handler.MyMapDetailHandler;
import com.bitcamp.goodplace.handler.MyMapListHandler;
import com.bitcamp.goodplace.handler.MyMapUpdateHandler;
import com.bitcamp.goodplace.handler.PlaceAddHandler;
import com.bitcamp.goodplace.handler.PlaceDeleteHandler;
import com.bitcamp.goodplace.handler.PlaceListHandler;
import com.bitcamp.goodplace.handler.RealTimeRankHandler;
import com.bitcamp.goodplace.handler.SearchHashtagHandler;
import com.bitcamp.goodplace.handler.SearchThemeHandler;
import com.bitcamp.goodplace.handler.SearchUserHandler;
import com.bitcamp.goodplace.handler.UserAddHandler;
import com.bitcamp.goodplace.handler.UserDeleteHandler;
import com.bitcamp.goodplace.handler.UserDetailHandler;
import com.bitcamp.goodplace.handler.UserFollowAddHandler;
import com.bitcamp.goodplace.handler.UserFollowersListHandler;
import com.bitcamp.goodplace.handler.UserListHandler;
import com.bitcamp.goodplace.handler.UserRankHandler;
import com.bitcamp.goodplace.handler.UserUpdateHandler;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.menu.MenuItem;
import com.bitcamp.util.Prompt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
  List<User> userList = new ArrayList<>();
  HashMap<String, Command> commandMap = new HashMap<>();

  public App() {
    commandMap.put("/auth/login", new AuthLoginHandler(userList));
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/displayLoginUer", new AuthDisplayLoginUserHandler());

    commandMap.put("/user/add", new UserAddHandler(userList));
    commandMap.put("/user/delete", new UserDeleteHandler(userList));
    commandMap.put("/user/detail", new UserDetailHandler(userList));
    commandMap.put("/user/list", new UserListHandler(userList));
    commandMap.put("/user/update", new UserUpdateHandler(userList));

    commandMap.put("/myMap/add", new MyMapAddHandler());
    commandMap.put("/myMap/delete", new MyMapDeleteHandler());
    commandMap.put("/myMap/list", new MyMapListHandler());
    commandMap.put("/myMap/detail", new MyMapDetailHandler());
    commandMap.put("/myMap/update", new MyMapUpdateHandler());

    commandMap.put("/bookmark/add", new BookmarkAddHandler());
    commandMap.put("/bookmark/delete", new BookmarkDeleteHandler());
    commandMap.put("/bookmark/list", new BookmarkListHandler());

    commandMap.put("/place/add", new PlaceAddHandler());
    commandMap.put("/place/delete", new PlaceDeleteHandler());
    commandMap.put("/place/list", new PlaceListHandler());

    commandMap.put("/search/list", new FullThemeListHandler(userList));
    commandMap.put("/search/searchTheme", new SearchThemeHandler(userList));
    commandMap.put("/search/searchUser", new SearchUserHandler(userList));
    commandMap.put("/search/searchHashtag", new SearchHashtagHandler(userList));

    commandMap.put("/following/add", new UserFollowAddHandler(userList));
    commandMap.put("/following/list", new UserFollowersListHandler(userList));

    commandMap.put("/rank/themeRank", new RealTimeRankHandler(userList));
    commandMap.put("/rank/userRank", new UserRankHandler(userList));
  }

  public static void main(String[] args) {
    App app = new App();
    app.service();
  }

  void service() {
    loadObject("user.json",userList,User.class);

    createMenu().execute(commandMap);
    Prompt.close();

    saveObjects("user.json",userList);
  }

  public <E> void loadObject(String fileName, List<E> list, Class<E> domainType) {
    try (BufferedReader in = new BufferedReader(new FileReader(fileName, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }
      Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
      Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

      list.addAll(collection);

      System.out.printf("%s 데이터 로딩 완료!\n", fileName);

    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", fileName);
    }
  }

  private void saveObjects(String filepath, List<?> list) {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(new FileWriter(filepath, Charset.forName("UTF-8"))))) {

      out.print(new Gson().toJson(list));

      System.out.printf("%s 데이터 출력 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n", filepath);
      e.printStackTrace();
    }
  }

  Menu createMenu() {
    MenuGroup mg = new MenuGroup("메인 메뉴");
    mg.setPrevMenuTitle("종료");

    mg.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mg.add(new MenuItem("내 정보", Menu.ACCESS_GENERAL, "/auth/displayLoginUer"));
    mg.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/logout"));

    createUserMenu(mg);
    createMyMapMenu(mg);
    //    createPlaceMenu(mg);
    createsearchMenu(mg);
    createBookmarkMenu(mg);
    createRankMenu(mg);
    createFollowMenu(mg);

    return mg;
  }

  private Menu createUserMenu(MenuGroup mg) {
    MenuGroup user = new MenuGroup("회원(회원가입)", Menu.ACCESS_LOGOUT | Menu.ACCESS_ADMIN);
    user.add(new MenuItem("회원가입", Menu.ACCESS_LOGOUT, "/user/add"));
    user.add(new MenuItem("회원목록", Menu.ACCESS_ADMIN, "/user/list"));
    user.add(new MenuItem("회원상세", Menu.ACCESS_ADMIN, "/user/detail"));
    user.add(new MenuItem("회원변경", Menu.ACCESS_ADMIN, "/user/update"));
    user.add(new MenuItem("회원삭제", Menu.ACCESS_ADMIN, "/user/delete"));

    mg.add(user);
    return user;
  }

  private void createMyMapMenu(MenuGroup mg) {
    MenuGroup myMap = new MenuGroup("나만의 지도", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);

    myMap.add(new MenuItem("테마 등록", "/myMap/add"));
    myMap.add(new MenuItem("테마 목록", "/myMap/list"));
    myMap.add(new MenuItem("테마 상세보기", "/myMap/detail"));
//    myMap.add(new MenuItem("테마 수정", "/myMap/update"));
    myMap.add(new MenuItem("테마 삭제", "/myMap/delete"));

    mg.add(myMap);
  }

  //  private void createPlaceMenu(MenuGroup mg) {
  //    MenuGroup savePlaceInTheme = new MenuGroup("테마에 장소 추가", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
  //
  //    savePlaceInTheme.add(new MenuItem("장소 등록", "/place/add"));
  //    savePlaceInTheme.add(new MenuItem("장소 목록", "/place/list"));
  //    savePlaceInTheme.add(new MenuItem("장소 삭제", "/place/delete"));
  //
  //    mg.add(savePlaceInTheme);
  //  }

  private void createsearchMenu(MenuGroup mg) {
    MenuGroup search = new MenuGroup("검색");

    search.add(new MenuItem("전체 테마 목록", "/search/list"));
    search.add(new MenuItem("테마 검색", "/search/searchTheme"));
    search.add(new MenuItem("해시태그 검색", "/search/searchHashtag"));
    search.add(new MenuItem("유저 검색", "/search/searchUser"));


    mg.add(search);
  }

  private void createBookmarkMenu(MenuGroup mg) {
    MenuGroup bookmark = new MenuGroup("북마크", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);

    bookmark.add(new MenuItem("북마크 등록", "/bookmark/add"));
    bookmark.add(new MenuItem("북마크 목록", "/bookmark/list"));
    bookmark.add(new MenuItem("북마크 삭제", "/bookmark/delete"));

    mg.add(bookmark);
  }

  private void createRankMenu(MenuGroup mg) {
    MenuGroup rank = new MenuGroup("순위");

    rank.add(new MenuItem("테마 순위", "/rank/themeRank"));
    rank.add(new MenuItem("유저 순위", "/rank/userRank"));

    mg.add(rank);
  }

  private void createFollowMenu(MenuGroup mg) {
    MenuGroup follow = new MenuGroup("팔로우");
    follow.add(new MenuItem("팔로우 추가", "/following/add"));
    follow.add(new MenuItem("팔로잉 목록 ", "/following/list"));

    mg.add(follow);
  }
}