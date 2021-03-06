package com.welcomeToJeju.moj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.menu.MenuFilter;
import com.welcomeToJeju.menu.MenuGroup;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.admin.AdminUserDeleteHandler;
import com.welcomeToJeju.moj.handler.admin.AdminUserDetailHandler;
import com.welcomeToJeju.moj.handler.admin.AdminUserListHandler;
import com.welcomeToJeju.moj.handler.admin.AdminUserUpdateHandler;
import com.welcomeToJeju.moj.handler.likedTheme.LikedThemeAddHandler;
import com.welcomeToJeju.moj.handler.likedTheme.LikedThemeDeleteHandler;
import com.welcomeToJeju.moj.handler.likedTheme.LikedThemeListHandler;
import com.welcomeToJeju.moj.handler.likedUser.LikedUserAddHandler;
import com.welcomeToJeju.moj.handler.likedUser.LikedUserDeleteHandler;
import com.welcomeToJeju.moj.handler.likedUser.LikedUserListHandler;
import com.welcomeToJeju.moj.handler.place.PlaceAddHandler;
import com.welcomeToJeju.moj.handler.place.PlaceDeleteHandler;
import com.welcomeToJeju.moj.handler.place.PlaceListHandler;
import com.welcomeToJeju.moj.handler.ranking.ThemeRankingHandler;
import com.welcomeToJeju.moj.handler.ranking.UserRankingHandler;
import com.welcomeToJeju.moj.handler.report.AdminReportThemeProcessHandler;
import com.welcomeToJeju.moj.handler.report.AdminReportUserProcessHandler;
import com.welcomeToJeju.moj.handler.report.ReportListHandler;
import com.welcomeToJeju.moj.handler.report.ReportThemeAddHandler;
import com.welcomeToJeju.moj.handler.report.ReportUserAddHandler;
import com.welcomeToJeju.moj.handler.search.SearchHashtagHandler;
import com.welcomeToJeju.moj.handler.search.SearchThemeHandler;
import com.welcomeToJeju.moj.handler.search.SearchUserHandler;
import com.welcomeToJeju.moj.handler.theme.AllThemeListHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeAddHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeDeleteHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeDetailHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeListHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeUpdateHandler;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.moj.handler.user.AuthLogoutHandler;
import com.welcomeToJeju.moj.handler.user.AuthUserInfoHandler;
import com.welcomeToJeju.moj.handler.user.UserAddHandler;
import com.welcomeToJeju.moj.handler.user.UserDeleteHandler;
import com.welcomeToJeju.moj.handler.user.UserUpdateHandler;
import com.welcomeToJeju.moj.listener.LoginListener;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class ClientApp {

  //  Connection con;
  SqlSession sqlSession;

  RequestAgent requestAgent;

  HashMap<String,Command> commandMap = new HashMap<>();

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.println("??????????????????????????????????? ??? ???????????????????????????????????");
        System.out.printf("%s ??? ??????!\n", menuId);
        e.printStackTrace();
        System.out.println("??????????????????????????????????? ??? ???????????????????????????????????");
      }
    }
  }

  // ?????????
  List<UserContextListener> userListeners = new ArrayList<>();

  public void addUserContextListener(UserContextListener userListener) {
    this.userListeners.add(userListener);
  }

  public void removeUserContextListener(UserContextListener userListener) {
    this.userListeners.remove(userListener);
  }
  //

  public ClientApp() throws Exception{
    // ????????? ????????? ????????? ?????? ??????
    //    requestAgent = new RequestAgent("127.0.0.1",8888);
    requestAgent = null;

    // DMBS??? ??????
    //    con = DriverManager.getConnection(
    //        "jdbc:mysql://localhost:3306/jejudb?user=jeju&password=1111");

    // Mybatis??? SqlSession ?????? ??????
    sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/welcomeToJeju/moj/conf/mybatis-config.xml")).openSession();

    // ????????? ????????? ????????? DAO ?????? ??????
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    ThemeDao themeDao = sqlSession.getMapper(ThemeDao.class);
    PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
    ReportDao reportDao = sqlSession.getMapper(ReportDao.class);

    // Command ?????? ??????
    // ??????
    commandMap.put("/user/add", new UserAddHandler(userDao, sqlSession));
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler(userDao)); //
    commandMap.put("/user/update", new UserUpdateHandler(userDao, sqlSession));
    commandMap.put("/user/delete", new UserDeleteHandler(userDao, themeDao, sqlSession));

    commandMap.put("/auth/login", new AuthLoginHandler(userDao, userListeners));
    commandMap.put("/auth/logout", new AuthLogoutHandler(userListeners));

    commandMap.put("/myTheme/add", new MyThemeAddHandler(themeDao, sqlSession));
    commandMap.put("/myTheme/list", new MyThemeListHandler(themeDao));
    commandMap.put("/myTheme/detail", new MyThemeDetailHandler(themeDao));
    commandMap.put("/myTheme/update", new MyThemeUpdateHandler(themeDao, sqlSession));
    commandMap.put("/myTheme/delete", new MyThemeDeleteHandler(themeDao, placeDao,sqlSession));

    // ?????? ?????? ??????
    commandMap.put("/theme/list", new AllThemeListHandler(themeDao));

    commandMap.put("/place/add", new PlaceAddHandler(placeDao, sqlSession));
    commandMap.put("/place/list", new PlaceListHandler(placeDao));
    commandMap.put("/place/delete", new PlaceDeleteHandler(themeDao, placeDao, sqlSession));
    // ?????? ?????? ??????

    commandMap.put("/likedTheme/add", new LikedThemeAddHandler(themeDao, placeDao, sqlSession));
    commandMap.put("/likedTheme/list", new LikedThemeListHandler(themeDao));
    commandMap.put("/likedTheme/delete", new LikedThemeDeleteHandler(themeDao, sqlSession));

    commandMap.put("/likedUser/add", new LikedUserAddHandler(userDao, sqlSession));
    commandMap.put("/likedUser/list", new LikedUserListHandler(userDao));
    commandMap.put("/likedUser/delete", new LikedUserDeleteHandler(userDao, sqlSession));

    commandMap.put("/search/theme", new SearchThemeHandler(themeDao, placeDao, sqlSession));
    commandMap.put("/search/user", new SearchUserHandler(userDao, themeDao, sqlSession));
    commandMap.put("/search/hashtag", new SearchHashtagHandler(themeDao, userDao));

    commandMap.put("/ranking/theme", new ThemeRankingHandler(themeDao));
    commandMap.put("/ranking/user", new UserRankingHandler(userDao));

    commandMap.put("/report/theme", new ReportThemeAddHandler(reportDao, themeDao, sqlSession));
    commandMap.put("/report/user", new ReportUserAddHandler(reportDao, userDao, sqlSession));
    commandMap.put("/report/list", new ReportListHandler(reportDao));

    // ?????????: ?????? ??????
    commandMap.put("/admin/reportThemeProcess", new AdminReportThemeProcessHandler(reportDao, themeDao, userDao, sqlSession));
    commandMap.put("/admin/reportUserProcess", new AdminReportUserProcessHandler(reportDao, userDao, sqlSession));

    // ?????????: ?????? ??????
    commandMap.put("/admin/userList", new AdminUserListHandler(userDao));
    commandMap.put("/admin/userDetail", new AdminUserDetailHandler(userDao));
    commandMap.put("/admin/userUpdate", new AdminUserUpdateHandler(userDao, sqlSession));
    commandMap.put("/admin/userDelete", new AdminUserDeleteHandler(userDao, themeDao, sqlSession));
  }

  MenuFilter menuFilter = menu -> (menu.getAccessScope() & AuthLoginHandler.getUserAccessLevel()) > 0;

  Menu createMenu() {
    MenuGroup mg = new MenuGroup("??????? ?????? ??????");
    mg.setPrevMenuTitle("??????");

    mg.setMenuFilter(menuFilter);

    mg.add(new MenuItem("?????????", Menu.ACCESS_LOGOUT, "/auth/login"));
    mg.add(new MenuItem("?????? ??????", Menu.ACCESS_LOGOUT, "/user/add"));

    mg.add(new MenuItem("??? ??????", Menu.ACCESS_GENERAL, "/auth/userinfo"));
    mg.add(new MenuItem("????????????", Menu.ACCESS_GENERAL, "/auth/logout"));

    mg.add(new MenuItem("?????? ?????? ??????", "/theme/list"));

    createMyThemeMenu(mg);
    createLikedThemeMenu(mg);
    createLikedUserMenu(mg);
    createSearchMenu(mg);
    createRankMenu(mg);
    createReportMenu(mg);
    createAdminUserMenu(mg);    // ?????????: ?????? ??????

    return mg;
  }

  private void createMyThemeMenu(MenuGroup mg) {
    MenuGroup myTheme = new MenuGroup("?????? ??????", Menu.ACCESS_GENERAL);
    myTheme.setMenuFilter(menuFilter);

    myTheme.add(new MenuItem("?????? ?????? ?????????", "/myTheme/add"));
    myTheme.add(new MenuItem("?????? ?????? ?????? ??????", "/myTheme/list"));
    myTheme.add(new MenuItem("?????? ?????? ?????? ??????", "/myTheme/detail"));

    mg.add(myTheme);
  }

  private void createLikedThemeMenu(MenuGroup mg) {
    MenuGroup likedTheme = new MenuGroup("???????????? ??????", Menu.ACCESS_GENERAL);
    likedTheme.setMenuFilter(menuFilter);

    likedTheme.add(new MenuItem("?????? ????????? ?????????", "/likedTheme/add"));
    likedTheme.add(new MenuItem("?????? ????????? ?????? ??????", "/likedTheme/list"));
    likedTheme.add(new MenuItem("?????? ????????? ????????????", "/likedTheme/delete"));

    mg.add(likedTheme);
  }

  private void createLikedUserMenu(MenuGroup mg) {
    MenuGroup likedUser = new MenuGroup("???????????? ??????", Menu.ACCESS_GENERAL);
    likedUser.setMenuFilter(menuFilter);

    likedUser.add(new MenuItem("?????? ????????? ?????????", "/likedUser/add"));
    likedUser.add(new MenuItem("?????? ????????? ?????? ??????", "/likedUser/list"));
    likedUser.add(new MenuItem("?????? ????????? ????????????", "/likedUser/delete"));

    mg.add(likedUser);
  }

  private void createSearchMenu(MenuGroup mg) {
    MenuGroup search = new MenuGroup("??????");
    search.setMenuFilter(menuFilter);

    search.add(new MenuItem("?????? ????????????", "/search/theme"));
    search.add(new MenuItem("?????? ????????????", "/search/user"));
    search.add(new MenuItem("???????????? ????????????", "/search/hashtag"));

    mg.add(search);
  }

  private void createRankMenu(MenuGroup mg) {
    MenuGroup rank = new MenuGroup("??????");
    rank.setMenuFilter(menuFilter);

    rank.add(new MenuItem("?????? ?????? ??????", "/ranking/theme"));  // ?????? ?????? ?????? ?????? ??? ??????
    rank.add(new MenuItem("?????? ?????? ??????", "/ranking/user"));   // ?????? ?????? ?????? ?????? ??? ??????

    mg.add(rank);
  }

  private void createReportMenu(MenuGroup mg) {
    MenuGroup report = new MenuGroup("??????", Menu.ACCESS_GENERAL);

    report.setMenuFilter(menuFilter);

    report.add(new MenuItem("?????? ????????????", "/report/theme"));
    report.add(new MenuItem("?????? ????????????", "/report/user"));
    report.add(new MenuItem("?????? ?????? ??????", "/report/list"));

    report.add(new MenuItem("?????? ?????? ????????????", Menu.ACCESS_ADMIN, "/admin/reportThemeProcess")); //
    report.add(new MenuItem("?????? ?????? ????????????", Menu.ACCESS_ADMIN, "/admin/reportUserProcess"));   //

    mg.add(report);
  }

  private Menu createAdminUserMenu(MenuGroup mg) {
    MenuGroup adminUser = new MenuGroup("?????? ??????", Menu.ACCESS_ADMIN);
    adminUser.setMenuFilter(menuFilter);

    adminUser.add(new MenuItem("?????? ?????? ??????", "/admin/userList"));
    adminUser.add(new MenuItem("?????? ?????? ??????", "/admin/userDetail"));

    mg.add(adminUser);

    return adminUser;
  }

  public void service() throws Exception{
    createMenu().execute();

    Prompt.close();

    // DBMS??? ????????? ??????
    //    con.close();

    // SqlSession ????????? ?????? ??????
    sqlSession.close();
  }

  public static void main(String[] args) throws Exception{
    ClientApp app = new ClientApp(); 
    app.addUserContextListener(new LoginListener());
    app.service();

    Prompt.close();
  }

}
