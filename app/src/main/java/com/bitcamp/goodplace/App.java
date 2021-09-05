package com.bitcamp.goodplace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bitcamp.goodplace.Handler.AuthDisplayLoginUserHandler;
import com.bitcamp.goodplace.Handler.AuthLoginHandler;
import com.bitcamp.goodplace.Handler.AuthLogoutHandler;
import com.bitcamp.goodplace.Handler.BookmarkAddHandler;
import com.bitcamp.goodplace.Handler.BookmarkDeleteHandler;
import com.bitcamp.goodplace.Handler.BookmarkListHandler;
import com.bitcamp.goodplace.Handler.Command;
import com.bitcamp.goodplace.Handler.FullThemeListHandler;
import com.bitcamp.goodplace.Handler.FullThemeSearchAreaHandler;
import com.bitcamp.goodplace.Handler.FullThemeSearchHashtagHandler;
import com.bitcamp.goodplace.Handler.MyMapAddHandler;
import com.bitcamp.goodplace.Handler.MyMapDeleteHandler;
import com.bitcamp.goodplace.Handler.MyMapListHandler;
import com.bitcamp.goodplace.Handler.MyMapUpdateHandler;
import com.bitcamp.goodplace.Handler.PlaceAddHandler;
import com.bitcamp.goodplace.Handler.PlaceDeleteHandler;
import com.bitcamp.goodplace.Handler.PlaceListHandler;
import com.bitcamp.goodplace.Handler.RankHandler;
import com.bitcamp.goodplace.Handler.UserAddHandler;
import com.bitcamp.goodplace.Handler.UserDeleteHandler;
import com.bitcamp.goodplace.Handler.UserDetailHandler;
import com.bitcamp.goodplace.Handler.UserListHandler;
import com.bitcamp.goodplace.Handler.UserUpdateHandler;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.util.Prompt;

public class App {
	List<User> userList = new ArrayList<>();
	HashMap<String,Command> commandMap = new HashMap<>();

	class MenuItem extends Menu{
		String menuId;
		public MenuItem(String title,String menuId) {
			this(title,ENABLE_ALL,menuId);
		}
		public MenuItem(String title,int enableState, String menuId) {
			super(title,enableState);
			this.menuId = menuId;
		}

		@Override
		public void execute() {
			Command command = commandMap.get(menuId);
			command.execute();
		}
		
	}
	public App() {
		commandMap.put("/auth/login", new AuthLoginHandler(userList));
		commandMap.put("/auth/logout", new AuthLogoutHandler());
		commandMap.put("/auth/displayLoginUer", new AuthDisplayLoginUserHandler());
		
		commandMap.put("/user/add", new UserAddHandler(userList));
		commandMap.put("/user/delete", new UserDeleteHandler(userList));
		commandMap.put("/user/detail", new UserDetailHandler(userList));
		commandMap.put("/user/list", new UserListHandler(userList));
		commandMap.put("/user/update", new UserUpdateHandler(userList));
		
		commandMap.put("/myMap/add",new MyMapAddHandler());
		commandMap.put("/myMap/delete",new MyMapDeleteHandler());
		commandMap.put("/myMap/list",new MyMapListHandler());
		commandMap.put("/myMap/update",new MyMapUpdateHandler());
		
		commandMap.put("/bookmark/add", new BookmarkAddHandler());
		commandMap.put("/bookmark/delete", new BookmarkDeleteHandler());
		commandMap.put("/bookmark/list", new BookmarkListHandler());
		
		commandMap.put("/place/add",new PlaceAddHandler());
		commandMap.put("/place/delete",new PlaceDeleteHandler());
		commandMap.put("/place/list",new PlaceListHandler());
		
		commandMap.put("/fullTheme/list", new FullThemeListHandler());
		commandMap.put("/fullTheme/searchArea", new FullThemeSearchAreaHandler());
		commandMap.put("/fullTheme/searchHashtag", new FullThemeSearchHashtagHandler());
		
		commandMap.put("/rank/themeRank", new RankHandler(userList));
	}
	public static void main(String[] args) {
		App app = new App();
		app.service();
	}
	void service() {
		createMenu().execute();
		Prompt.close();
	}
	
	Menu createMenu() {
		MenuGroup mg = new MenuGroup("메인 메뉴");
		mg.setPrevMenuTitle("종료");
		
		mg.add(new MenuItem("로그인",Menu.ENABLE_LOGOUT,"/auth/login"));
		mg.add(new MenuItem("내 정보",Menu.ENABLE_LOGIN,"/auth/displayLoginUer"));
		mg.add(new MenuItem("로그아웃",Menu.ENABLE_LOGIN,"/auth/logout"));
		
		createUserMenu(mg);
		createMyMapMenu(mg);
		createPlaceMenu(mg);
		createFullThemeMenu(mg);
		createBookmarkMenu(mg);
		createRankMenu(mg);

		return mg;
	}
	
	private void createUserMenu(MenuGroup mg) {
		MenuGroup user = new MenuGroup("회원(회원가입)");
		user.add(new MenuItem("회원가입",Menu.ENABLE_LOGOUT,"/user/add"));
		user.add(new MenuItem("회원목록",Menu.ENABLE_LOGIN,"/user/list"));
		user.add(new MenuItem("회원상세",Menu.ENABLE_LOGIN,"/user/detail"));
		user.add(new MenuItem("회원변경",Menu.ENABLE_LOGIN,"/user/update"));
		user.add(new MenuItem("회원삭제",Menu.ENABLE_LOGIN,"/user/delete"));

		mg.add(user);
	}
	private void createMyMapMenu(MenuGroup mg) {
		MenuGroup myMap = new MenuGroup("나만의 지도");

		myMap.add(new MenuItem("테마 등록",Menu.ENABLE_LOGIN,"/myMap/add"));
		myMap.add(new MenuItem("테마 목록",Menu.ENABLE_LOGIN,"/myMap/list"));
		myMap.add(new MenuItem("테마 수정",Menu.ENABLE_LOGIN,"/myMap/update"));
		myMap.add(new MenuItem("테마 삭제",Menu.ENABLE_LOGIN,"/myMap/delete"));
		
		mg.add(myMap);
	}
	private void createPlaceMenu(MenuGroup mg) {
		MenuGroup savePlaceInTheme = new MenuGroup("테마에 장소 추가");
		
		savePlaceInTheme.add(new MenuItem("장소 등록",Menu.ENABLE_LOGIN,"/place/add"));
		savePlaceInTheme.add(new MenuItem("장소 삭제",Menu.ENABLE_LOGIN,"/place/delete"));
		savePlaceInTheme.add(new MenuItem("장소 변경",Menu.ENABLE_LOGIN,"/place/list"));

		mg.add(savePlaceInTheme);
	}
	private void createFullThemeMenu(MenuGroup mg) {
		MenuGroup fullTheme = new MenuGroup("전체 테마 보기");

		fullTheme.add(new MenuItem("장소 테마 목록",Menu.ENABLE_ALL,"/fullTheme/add"));
		fullTheme.add(new MenuItem("해시태그 검색",Menu.ENABLE_ALL,"/fullTheme/searchHashtag"));
		fullTheme.add(new MenuItem("지역별 검색",Menu.ENABLE_ALL,"/fullTheme/searchArea"));

		mg.add(fullTheme);
	}
	private void createBookmarkMenu(MenuGroup mg) {
		MenuGroup bookmark = new MenuGroup("북마크");
		
		bookmark.add(new MenuItem("북마크 등록",Menu.ENABLE_LOGIN,"/bookmark/add"));
		bookmark.add(new MenuItem("북마크 목록",Menu.ENABLE_LOGIN,"/bookmark/list"));
		bookmark.add(new MenuItem("북마크 삭제",Menu.ENABLE_LOGIN,"/bookmark/delete"));

		mg.add(bookmark);
	}
	private void createRankMenu(MenuGroup mg) {
		MenuGroup rank = new MenuGroup("순위");
		
		rank.add(new MenuItem("테마 순위",Menu.ENABLE_LOGIN,"/rank/themeRank"));
		rank.add(new MenuItem("테마 순위",Menu.ENABLE_LOGIN,"/rank/themeRank"));
		
		mg.add(rank);
	}
}