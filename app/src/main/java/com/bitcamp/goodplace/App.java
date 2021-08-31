package com.bitcamp.goodplace;

import com.bitcamp.goodplace.Handler.AuthHandler;
import com.bitcamp.goodplace.Handler.BookmarkHandler;
import com.bitcamp.goodplace.Handler.FullThemeHandler;
import com.bitcamp.goodplace.Handler.MyMapHandler;
import com.bitcamp.goodplace.Handler.PlaceHandler;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.menu.Menu;
import com.bitcamp.menu.MenuGroup;
import com.bitcamp.util.Prompt;

public class App {
	public static void main(String[] args) {
		App app = new App();
		app.service();
	}
	void service() {
		createMenu().execute();
		Prompt.close();
	}
	
	Menu createMenu() {
		User user = new User();
		PlaceHandler placeHandler = new PlaceHandler(user);
		MyMapHandler myMapHandler = new MyMapHandler(user,placeHandler);
		BookmarkHandler bookmarkHandler = new BookmarkHandler(user);
		FullThemeHandler fullThemeHandler = new FullThemeHandler(user);
		AuthHandler authHandler = new AuthHandler();
		

		MenuGroup mg = new MenuGroup("메인 메뉴");
		mg.setPrevMenuTitle("종료");
		
		
/////////////////////////////////////////////////////////////////////////////////
		mg.add(new Menu("로그인") {
			@Override
			public void execute() {
				authHandler.login();
			}
		});
		mg.add(new Menu("내 정보") {
			@Override
			public void execute() {
				authHandler.displayLoginUser();
			}
		});
		mg.add(new Menu("로그아웃") {
			@Override
			public void execute() {
				authHandler.logout();
			}
		});
/////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////
		MenuGroup savePlaceInTheme = new MenuGroup("테마에 장소 추가");
		savePlaceInTheme.add(new Menu("장소 등록") {
			@Override
			public void execute() {
				placeHandler.add();
			}
		});
		savePlaceInTheme.add(new Menu("장소 목록") {
			@Override
			public void execute() {
				placeHandler.list();
			}
		});
		savePlaceInTheme.add(new Menu("장소 삭제") {
			@Override
			public void execute() {
				placeHandler.delete();
			}
		});
		mg.add(savePlaceInTheme);

///////////////////////////////////////////////////////////////////////////////
		MenuGroup fullTheme = new MenuGroup("전체 테마 보기");

		fullTheme.add(new Menu("전체 테마 목록") {
			@Override
			public void execute() {
				fullThemeHandler.list();
			}
		});
		fullTheme.add(new Menu("해시태그 검색") {
			@Override
			public void execute() {
				fullThemeHandler.searchHashtag();
			}
		});
		mg.add(fullTheme);
///////////////////////////////////////////////////////////////////////////////
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

		mg.add(bookmark);
///////////////////////////////////////////////////////////////////////////////
		MenuGroup rank = new MenuGroup("순위");
		rank.add(new Menu("실시간 테마 순위") {
			@Override
			public void execute() {
				bookmarkHandler.delete();
			}
		});
		rank.add(new Menu("전체 테마 순위") {
			@Override
			public void execute() {
				bookmarkHandler.delete();
			}
		});
		
		mg.add(rank);

///////////////////////////////////////////////////////////////////////////////
		return mg;
	}
}