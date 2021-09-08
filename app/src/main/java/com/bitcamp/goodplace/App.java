package com.bitcamp.goodplace;

import java.util.ArrayList;
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
import com.bitcamp.goodplace.handler.FullThemeSearchHashtagHandler;
import com.bitcamp.goodplace.handler.FullThemeSearchUserHandler;
import com.bitcamp.goodplace.handler.MyMapAddHandler;
import com.bitcamp.goodplace.handler.MyMapDeleteHandler;
import com.bitcamp.goodplace.handler.MyMapListHandler;
import com.bitcamp.goodplace.handler.MyMapUpdateHandler;
import com.bitcamp.goodplace.handler.PlaceAddHandler;
import com.bitcamp.goodplace.handler.PlaceDeleteHandler;
import com.bitcamp.goodplace.handler.PlaceListHandler;
import com.bitcamp.goodplace.handler.RankHandler;
import com.bitcamp.goodplace.handler.UserAddHandler;
import com.bitcamp.goodplace.handler.UserDeleteHandler;
import com.bitcamp.goodplace.handler.UserDetailHandler;
import com.bitcamp.goodplace.handler.UserListHandler;
import com.bitcamp.goodplace.handler.UserUpdateHandler;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.util.Prompt;

public class App {
	List<User> userList = new ArrayList<>();
	HashMap<String,Command> commandMap = new HashMap<>();

	class MenuItem extends Menu{
		String menuId;
		public MenuItem(String title,String menuId) {
			super(title);
			this.menuId = menuId;
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
		
		commandMap.put("/fullTheme/list", new FullThemeListHandler(userList));
		commandMap.put("/fullTheme/searchUser", new FullThemeSearchUserHandler(userList));
		commandMap.put("/fullTheme/searchHashtag", new FullThemeSearchHashtagHandler(userList));
		
		commandMap.put("/rank/themeRank", new RankHandler(userList));
	}
	public static void main(String[] args) {
		App app = new App();
		app.service();
	}
	void service() {
//		KakaoMapApi kakao = new KakaoMapApi();
//		KakaoVo kakaoVo= kakao.searchPlace("강남불백");
//		System.out.println(kakaoVo.getAddress_name());
//		System.out.println(kakaoVo.getX());
//		System.out.println(kakaoVo.getY());
		createMenu().execute();
		Prompt.close();
	}
	
	Menu createMenu() {
		MenuGroup mg = new MenuGroup("메인 메뉴");
		mg.setPrevMenuTitle("종료");
		
		mg.add(new MenuItem("로그인",Menu.ACCESS_LOGOUT,"/auth/login"));
		mg.add(new MenuItem("내 정보",Menu.ACCESS_GENERAL,"/auth/displayLoginUer"));
		mg.add(new MenuItem("로그아웃",Menu.ACCESS_GENERAL,"/auth/logout"));
		
		createUserMenu(mg);
		createMyMapMenu(mg);
		createPlaceMenu(mg);
		createFullThemeMenu(mg);
		createBookmarkMenu(mg);
		createRankMenu(mg);

		return mg;
	}
	
	private void createUserMenu(MenuGroup mg) {
		MenuGroup user = new MenuGroup("회원(회원가입)",Menu.ACCESS_LOGOUT | Menu.ACCESS_ADMIN);
		user.add(new MenuItem("회원가입",Menu.ACCESS_LOGOUT,"/user/add"));
		user.add(new MenuItem("회원목록",Menu.ACCESS_ADMIN,"/user/list"));
		user.add(new MenuItem("회원상세",Menu.ACCESS_ADMIN,"/user/detail"));
		user.add(new MenuItem("회원변경",Menu.ACCESS_ADMIN,"/user/update"));
		user.add(new MenuItem("회원삭제",Menu.ACCESS_ADMIN,"/user/delete"));

		mg.add(user);
	}
	private void createMyMapMenu(MenuGroup mg) {
		MenuGroup myMap = new MenuGroup("나만의 지도",Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);

		myMap.add(new MenuItem("테마 등록","/myMap/add"));
		myMap.add(new MenuItem("테마 목록","/myMap/list"));
		myMap.add(new MenuItem("테마 수정","/myMap/update"));
		myMap.add(new MenuItem("테마 삭제","/myMap/delete"));
		
		mg.add(myMap);
	}
	private void createPlaceMenu(MenuGroup mg) {
		MenuGroup savePlaceInTheme = new MenuGroup("테마에 장소 추가",Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
		
		savePlaceInTheme.add(new MenuItem("장소 등록","/place/add"));
		savePlaceInTheme.add(new MenuItem("장소 목록","/place/list"));
		savePlaceInTheme.add(new MenuItem("장소 삭제","/place/delete"));

		mg.add(savePlaceInTheme);
	}
	private void createFullThemeMenu(MenuGroup mg) {
		MenuGroup fullTheme = new MenuGroup("전체 테마 보기");

		fullTheme.add(new MenuItem("전체 테마 목록","/fullTheme/list"));
		fullTheme.add(new MenuItem("해시태그 검색","/fullTheme/searchHashtag"));
		fullTheme.add(new MenuItem("유저 검색","/fullTheme/searchUser"));

		mg.add(fullTheme);
	}
	private void createBookmarkMenu(MenuGroup mg) {
		MenuGroup bookmark = new MenuGroup("북마크",Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
		
		bookmark.add(new MenuItem("북마크 등록","/bookmark/add"));
		bookmark.add(new MenuItem("북마크 목록","/bookmark/list"));
		bookmark.add(new MenuItem("북마크 삭제","/bookmark/delete"));

		mg.add(bookmark);
	}
	private void createRankMenu(MenuGroup mg) {
		MenuGroup rank = new MenuGroup("순위");
		
		rank.add(new MenuItem("테마 순위","/rank/themeRank"));
		rank.add(new MenuItem("테마 순위","/rank/themeRank"));
		
		mg.add(rank);
	}
}