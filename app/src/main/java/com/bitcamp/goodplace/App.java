package com.bitcamp.goodplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.bitcamp.context.ApplicationContextListener;
import com.bitcamp.context.UserContextListener;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.goodplace.handler.AllThemeListHandler;
import com.bitcamp.goodplace.handler.AuthDisplayLoginUserHandler;
import com.bitcamp.goodplace.handler.AuthLoginHandler;
import com.bitcamp.goodplace.handler.AuthLogoutHandler;
import com.bitcamp.goodplace.handler.Command;
import com.bitcamp.goodplace.handler.CommandRequest;
import com.bitcamp.goodplace.handler.LikedThemeAddHandler;
import com.bitcamp.goodplace.handler.LikedThemeDeleteHandler;
import com.bitcamp.goodplace.handler.LikedThemeListHandler;
import com.bitcamp.goodplace.handler.LikedUserAddHandler;
import com.bitcamp.goodplace.handler.LikedUserDeleteHandler;
import com.bitcamp.goodplace.handler.LikedUserListHandler;
import com.bitcamp.goodplace.handler.MyThemeAddHandler;
import com.bitcamp.goodplace.handler.MyThemeDeleteHandler;
import com.bitcamp.goodplace.handler.MyThemeDetailHandler;
import com.bitcamp.goodplace.handler.MyThemeListHandler;
import com.bitcamp.goodplace.handler.MyThemeUpdateHandler;
import com.bitcamp.goodplace.handler.PlaceAddHandler;
import com.bitcamp.goodplace.handler.PlaceDeleteHandler;
import com.bitcamp.goodplace.handler.PlaceListHandler;
import com.bitcamp.goodplace.handler.RealTimeRankHandler;
import com.bitcamp.goodplace.handler.ReportAddThemeHandler;
import com.bitcamp.goodplace.handler.ReportAddUserHandler;
import com.bitcamp.goodplace.handler.ReportMyListHandler;
import com.bitcamp.goodplace.handler.ReportThemeProcessingHandler;
import com.bitcamp.goodplace.handler.ReportUserProcessingHandler;
import com.bitcamp.goodplace.handler.SearchHashtagHandler;
import com.bitcamp.goodplace.handler.SearchThemeHandler;
import com.bitcamp.goodplace.handler.SearchUserHandler;
import com.bitcamp.goodplace.handler.UserAddHandler;
import com.bitcamp.goodplace.handler.UserDeleteHandler;
import com.bitcamp.goodplace.handler.UserDetailHandler;
import com.bitcamp.goodplace.handler.UserEditHandler;
import com.bitcamp.goodplace.handler.UserListHandler;
import com.bitcamp.goodplace.handler.UserRankHandler;
import com.bitcamp.goodplace.handler.UserUnregisterHandler;
import com.bitcamp.goodplace.handler.UserUpdateHandler;
import com.bitcamp.goodplace.listener.FileListener;
import com.bitcamp.goodplace.listener.LoginListener;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.util.Prompt;

public class App {
  List<User> userList = new ArrayList<>();
  public static List<Report> reportList = new ArrayList<>();
  List<ReportTheme> reportThemeList = new ArrayList<>();
  List<ReportUser> reportUserList = new ArrayList<>();
  HashMap<String, Command> commandMap = new HashMap<>();
  List<Theme> themeList = new ArrayList<>();

  List<ApplicationContextListener> listeners = new ArrayList<>();
  List<UserContextListener> userListeners = new ArrayList<>();


  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }

  public void addUserContextListener(UserContextListener userListener) {
    this.userListeners.add(userListener);
  }

  public void removeUserContextListener(UserContextListener userListener) {
    this.userListeners.remove(userListener);
  }

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int enableState, String menuId) {
      super(title, enableState);
      this.menuId = menuId;
    }

    @Override

    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s ????????? ???????????? ??? ?????? ??????!", menuId);
        e.printStackTrace();
      } 
    }

  }

  public App() {
    commandMap.put("/auth/login", new AuthLoginHandler(userList, userListeners));
    commandMap.put("/auth/logout", new AuthLogoutHandler(userListeners));
    commandMap.put("/auth/displayLoginUer", new AuthDisplayLoginUserHandler());
    commandMap.put("/theme/all", new AllThemeListHandler(userList));
    commandMap.put("/auth/unregistered", new UserUnregisterHandler(userList));
    commandMap.put("/auth/edit", new UserEditHandler());

    // ?????? ?????? ?????? ??????

    commandMap.put("/user/add", new UserAddHandler(userList));
    commandMap.put("/user/delete", new UserDeleteHandler(userList));
    commandMap.put("/user/detail", new UserDetailHandler(userList));
    commandMap.put("/user/list", new UserListHandler(userList));
    commandMap.put("/user/update", new UserUpdateHandler(userList));

    commandMap.put("/myTheme/add", new MyThemeAddHandler(userList,themeList));
    commandMap.put("/myTheme/delete", new MyThemeDeleteHandler(userList));
    commandMap.put("/myTheme/list", new MyThemeListHandler(userList));
    commandMap.put("/myTheme/detail", new MyThemeDetailHandler(userList));
    commandMap.put("/myTheme/update", new MyThemeUpdateHandler(userList));

    commandMap.put("/likedTheme/add", new LikedThemeAddHandler(userList));
    commandMap.put("/likedTheme/delete", new LikedThemeDeleteHandler(userList));
    commandMap.put("/likedTheme/list", new LikedThemeListHandler());

    commandMap.put("/place/add", new PlaceAddHandler());
    commandMap.put("/place/delete", new PlaceDeleteHandler());
    commandMap.put("/place/list", new PlaceListHandler());


    commandMap.put("/search/searchTheme", new SearchThemeHandler(userList, themeList));
    commandMap.put("/search/searchUser", new SearchUserHandler(userList));
    commandMap.put("/search/searchHashtag", new SearchHashtagHandler(userList, themeList));

    commandMap.put("/likedUser/add", new LikedUserAddHandler(userList));
    commandMap.put("/likedUser/list", new LikedUserListHandler(userList));
    commandMap.put("/likedUser/delete", new LikedUserDeleteHandler());

    commandMap.put("/rank/themeRank", new RealTimeRankHandler(userList));
    commandMap.put("/rank/userRank", new UserRankHandler(userList));

    commandMap.put("/report/theme", new ReportAddThemeHandler(userList,reportThemeList));
    commandMap.put("/report/user", new ReportAddUserHandler(userList,reportUserList));
    commandMap.put("/report/list", new ReportMyListHandler(reportList));
    commandMap.put("/report/themeProcess", new ReportThemeProcessingHandler(userList,reportThemeList));
    commandMap.put("/report/userProcess", new ReportUserProcessingHandler(userList,reportUserList));
  }

  public static void main(String[] args) {
    App app = new App();
    app.addApplicationContextListener(new FileListener());
    app.addUserContextListener(new LoginListener());
    app.service();

  }

  private void notifyOnApplicationStarted() {
    HashMap<String,Object> params = new HashMap<>();
    params.put("userList", userList);
    params.put("reportThemeList", reportThemeList);
    params.put("reportUserList", reportUserList);

    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    HashMap<String,Object> params = new HashMap<>();
    params.put("userList", userList);
    params.put("reportThemeList", reportThemeList);
    params.put("reportUserList", reportUserList);

    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }

  void service() {
    notifyOnApplicationStarted();
    reportList.addAll(reportThemeList);
    reportList.addAll(reportUserList);


    createMenu().execute();
    Prompt.close();
    notifyOnApplicationEnded();
  }


  Menu createMenu() {
    MenuGroup mg = new MenuGroup("?????? ??????");
    mg.setPrevMenuTitle("??????");

    mg.add(new MenuItem("?????????", Menu.ACCESS_LOGOUT, "/auth/login"));
    mg.add(new MenuItem("?????? ????????????", Menu.ACCESS_LOGOUT, "/user/add"));
    mg.add(new MenuItem("??? ??????", Menu.ACCESS_GENERAL, "/auth/displayLoginUer"));
    //    mg.add(new MenuItem(""))
    mg.add(new MenuItem("????????????", Menu.ACCESS_GENERAL, "/auth/logout"));
    mg.add(new MenuItem("?????? ?????? ??????", "/theme/all"));

    createUserMenu(mg);
    createMyMapMenu(mg);
    //    createPlaceMenu(mg);
    createSearchMenu(mg);
    createLikedThemeMenu(mg);
    createLikedUserMenu(mg);
    createRankMenu(mg);
    createReportMenu(mg);


    return mg;
  }


  private Menu createUserMenu(MenuGroup mg) {
    MenuGroup user = new MenuGroup("????????????",Menu.ACCESS_ADMIN);
    user.add(new MenuItem("?????? ????????????", Menu.ACCESS_ADMIN, "/user/list"));
    user.add(new MenuItem("?????? ????????????", Menu.ACCESS_ADMIN, "/user/detail"));
    user.add(new MenuItem("?????? ????????????", Menu.ACCESS_ADMIN, "/user/update"));
    user.add(new MenuItem("?????? ????????????", Menu.ACCESS_ADMIN, "/user/delete"));

    mg.add(user);
    return user;
  }

  private void createMyMapMenu(MenuGroup mg) {
    MenuGroup myMap = new MenuGroup("?????? ??????", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);

    myMap.add(new MenuItem("?????? ?????????", "/myTheme/add"));
    myMap.add(new MenuItem("?????? ????????????", "/myTheme/list"));
    myMap.add(new MenuItem("?????? ????????????", "/myTheme/detail"));
    //    myMap.add(new MenuItem("?????? ????????????", "/myTheme/update"));
    myMap.add(new MenuItem("?????? ????????????", "/myTheme/delete"));

    mg.add(myMap);
  }

  //  private void createPlaceMenu(MenuGroup mg) {
  //    MenuGroup savePlaceInTheme = new MenuGroup("????????? ?????? ??????", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
  //
  //    savePlaceInTheme.add(new MenuItem("?????? ??????", "/place/add"));
  //    savePlaceInTheme.add(new MenuItem("?????? ??????", "/place/list"));
  //    savePlaceInTheme.add(new MenuItem("?????? ??????", "/place/delete"));
  //
  //    mg.add(savePlaceInTheme);
  //  }

  private void createSearchMenu(MenuGroup mg) {
    MenuGroup search = new MenuGroup("????????????");


    search.add(new MenuItem("?????? ????????????", "/search/searchTheme"));
    // ?????? ????????? ???????????? ????????????
    search.add(new MenuItem("?????? ????????????", "/search/searchUser"));
    search.add(new MenuItem("???????????? ????????????", "/search/searchHashtag"));


    mg.add(search);
  }

  private void createLikedThemeMenu(MenuGroup mg) {
    MenuGroup like = new MenuGroup("???????????? ??????", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);

    like.add(new MenuItem("????????? ????????????", "/likedTheme/add"));
    like.add(new MenuItem("????????? ????????????", "/likedTheme/list"));
    like.add(new MenuItem("????????? ????????????", "/likedTheme/delete"));

    mg.add(like);
  }

  private void createRankMenu(MenuGroup mg) {
    MenuGroup rank = new MenuGroup("????????????");

    rank.add(new MenuItem("?????? ????????????", "/rank/themeRank")); // ?????? ?????? ?????? ?????? ????????? ??????
    rank.add(new MenuItem("?????? ????????????", "/rank/userRank")); // ?????? ?????? ?????? ????????? ??????

    mg.add(rank);
  }

  private void createLikedUserMenu(MenuGroup mg) {
    MenuGroup follow = new MenuGroup("???????????? ??????", Menu.ACCESS_GENERAL);
    follow.add(new MenuItem("???????????? ?????? ????????????", "/likedUser/add"));
    follow.add(new MenuItem("???????????? ?????? ????????????", "/likedUser/list"));
    follow.add(new MenuItem("???????????? ?????? ????????????", "/likedUser/delete"));

    mg.add(follow);
  }

  private void createReportMenu(MenuGroup mg) {
    MenuGroup report = new MenuGroup("????????????", Menu.ACCESS_GENERAL);
    report.add(new MenuItem("?????? ??????", "/report/theme"));
    report.add(new MenuItem("?????? ??????", "/report/user"));
    report.add(new MenuItem("?????? ?????? ??????", "/report/list"));
    report.add(new MenuItem("?????? ?????? ??????", Menu.ACCESS_ADMIN,"/report/themeProcess"));
    report.add(new MenuItem("?????? ?????? ??????", Menu.ACCESS_ADMIN,"/report/userProcess"));
    mg.add(report);
  }


}