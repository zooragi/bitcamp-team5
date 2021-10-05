package com.bitcamp.goodplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bitcamp.context.UserContextListener;
import com.bitcamp.goodplace.handler.AuthLoginHandler;
import com.bitcamp.goodplace.handler.Command;
import com.bitcamp.goodplace.handler.CommandRequest;
import com.bitcamp.goodplace.handler.MyThemeAddHandler;
import com.bitcamp.goodplace.handler.MyThemeDeleteHandler;
import com.bitcamp.goodplace.handler.MyThemeDetailHandler;
import com.bitcamp.goodplace.handler.MyThemeListHandler;
import com.bitcamp.goodplace.handler.UserAddHandler;
import com.bitcamp.goodplace.listener.LoginListener;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuFilter;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class ClientApp {
	static RequestAgent requestAgent;
	
  HashMap<String,Command> commandMap = new HashMap<>();

  List<UserContextListener> userListeners = new ArrayList<>();

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
        System.out.printf("%s 명령을 실행하는 중 오류 발생!", menuId);
        e.printStackTrace();
      } 
    }

  }
	
  public void addUserContextListener(UserContextListener userListener) {
    this.userListeners.add(userListener);
  }

  public void removeUserContextListener(UserContextListener userListener) {
    this.userListeners.remove(userListener);
  }
  
  public ClientApp() throws Exception{
  	requestAgent = new RequestAgent("127.0.0.1",8888);
  	
    commandMap.put("/user/add", new UserAddHandler(requestAgent));
    commandMap.put("/auth/login", new AuthLoginHandler(requestAgent,userListeners));
    commandMap.put("/myTheme/add", new MyThemeAddHandler(requestAgent));
    commandMap.put("/myTheme/list", new MyThemeListHandler(requestAgent));
    commandMap.put("/myTheme/detail", new MyThemeDetailHandler(requestAgent));
    commandMap.put("/myTheme/delete", new MyThemeDeleteHandler(requestAgent));

  }
  
  MenuFilter menuFilter = menu -> (menu.getAccessScope() & AuthLoginHandler.getUseAccessLevel()) > 0;
  
  Menu createMenu() {
    MenuGroup mg = new MenuGroup("메인 메뉴");
    mg.setPrevMenuTitle("종료");
    mg.setMenuFilter(menuFilter);
    mg.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mg.add(new MenuItem("회원 가입하기", Menu.ACCESS_LOGOUT, "/user/add"));
    mg.add(new MenuItem("내 정보", Menu.ACCESS_GENERAL, "/auth/displayLoginUer"));
    mg.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/logout"));
    mg.add(new MenuItem("전체 테마 보기", "/theme/all"));

    createUserMenu(mg);
    createMyMapMenu(mg);
    createSearchMenu(mg);
    createLikedThemeMenu(mg);
    createLikedUserMenu(mg);
    createRankMenu(mg);
    createReportMenu(mg);


    return mg;
  }


  private Menu createUserMenu(MenuGroup mg) {
    MenuGroup user = new MenuGroup("회원관리",Menu.ACCESS_ADMIN);
    user.setMenuFilter(menuFilter);
    user.add(new MenuItem("회원 목록보기", Menu.ACCESS_ADMIN, "/user/list"));
    user.add(new MenuItem("회원 상세보기", Menu.ACCESS_ADMIN, "/user/detail"));
    user.add(new MenuItem("회원 수정하기", Menu.ACCESS_ADMIN, "/user/update"));
    user.add(new MenuItem("회원 삭제하기", Menu.ACCESS_ADMIN, "/user/delete"));

    mg.add(user);
    return user;
  }

  private void createMyMapMenu(MenuGroup mg) {
    MenuGroup myMap = new MenuGroup("나의 테마", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
    myMap.setMenuFilter(menuFilter);
    myMap.add(new MenuItem("테마 만들기", "/myTheme/add"));
    myMap.add(new MenuItem("테마 목록보기", "/myTheme/list"));
    myMap.add(new MenuItem("테마 상세보기", "/myTheme/detail"));
    myMap.add(new MenuItem("테마 삭제하기", "/myTheme/delete"));

    mg.add(myMap);
  }

  private void createSearchMenu(MenuGroup mg) {
    MenuGroup search = new MenuGroup("검색하기");

    search.setMenuFilter(menuFilter);
    search.add(new MenuItem("테마 검색하기", "/search/searchTheme"));
    search.add(new MenuItem("유저 검색하기", "/search/searchUser"));
    search.add(new MenuItem("해시태그 검색하기", "/search/searchHashtag"));


    mg.add(search);
  }

  private void createLikedThemeMenu(MenuGroup mg) {
    MenuGroup like = new MenuGroup("좋아하는 테마", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
    like.setMenuFilter(menuFilter);

    like.add(new MenuItem("좋아요 등록하기", "/likedTheme/add"));
    like.add(new MenuItem("좋아요 목록보기", "/likedTheme/list"));
    like.add(new MenuItem("좋아요 삭제하기", "/likedTheme/delete"));

    mg.add(like);
  }

  private void createRankMenu(MenuGroup mg) {
    MenuGroup rank = new MenuGroup("순위보기");

    rank.setMenuFilter(menuFilter);

    rank.add(new MenuItem("테마 순위보기", "/rank/themeRank")); // 전체 테마 검색 기준 조횟수 증가
    rank.add(new MenuItem("유저 순위보기", "/rank/userRank")); // 유저 검색 기준 조횟수 증가

    mg.add(rank);
  }

  private void createLikedUserMenu(MenuGroup mg) {
    MenuGroup follow = new MenuGroup("좋아하는 유저", Menu.ACCESS_GENERAL);
    follow.setMenuFilter(menuFilter);

    follow.add(new MenuItem("좋아하는 유저 등록하기", "/likedUser/add"));
    follow.add(new MenuItem("좋아하는 유저 목록보기", "/likedUser/list"));
    follow.add(new MenuItem("좋아하는 유저 삭제하기", "/likedUser/delete"));

    mg.add(follow);
  }

  private void createReportMenu(MenuGroup mg) {
    MenuGroup report = new MenuGroup("신고하기", Menu.ACCESS_GENERAL);
    
    report.setMenuFilter(menuFilter);

    report.add(new MenuItem("테마 신고", "/report/theme"));
    report.add(new MenuItem("유저 신고", "/report/user"));
    report.add(new MenuItem("나의 신고 목록", "/report/list"));
    report.add(new MenuItem("테마 신고 처리", Menu.ACCESS_ADMIN,"/report/themeProcess"));
    report.add(new MenuItem("유저 신고 처리", Menu.ACCESS_ADMIN,"/report/userProcess"));
    mg.add(report);
  }
  
  public void service() throws Exception{
    createMenu().execute();

    requestAgent.request("quit", null);

    Prompt.close();
  }
  
	public static void main(String[] args) throws Exception{
    System.out.println("[PMS 클라이언트]");

    ClientApp app = new ClientApp(); 
    app.addUserContextListener(new LoginListener());
    app.service();

    Prompt.close();
	}
  
}
