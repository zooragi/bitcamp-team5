package com.bitcamp.goodplace;

import java.util.ArrayList;

import com.bitcamp.goodplace.Handler.BookmarkHandler;
import com.bitcamp.goodplace.Handler.MyMapHandler;
import com.bitcamp.goodplace.domain.Theme;
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
		MyMapHandler myMapHandler = new MyMapHandler(user);
		BookmarkHandler bookmarkHandler = new BookmarkHandler(user);

		MenuGroup mg = new MenuGroup("메인 메뉴");
		mg.setPrevMenuTitle("종료");
		
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

		myMap.add(addThemeDetailMenuGroup(myMapHandler));
		mg.add(myMap);

///////////////////////////////////////////////////////////////////////////////
		MenuGroup fullTheme = new MenuGroup("전체 테마 보기");

		fullTheme.add(new Menu("전체테마 목록조회") {
			@Override
			public void execute() {
				bookmarkHandler.list();
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
		mg.add(rank);

///////////////////////////////////////////////////////////////////////////////
		return mg;
	}
	
	Menu addThemeDetailMenuGroup(MyMapHandler myMapHandler) {
		MenuGroup themeDetail = new MenuGroup("테마 상세 보기");
		themeDetail.add(new Menu("검색") {
			@Override
			public void execute() {
				while(true) {
					String input = Prompt.inputString("검색하시오 : ");
					if(input.length() == 0) {
						return;
					}
					Theme theme = myMapHandler.findByTitle(input);
					if(myMapHandler.findByTitle(input) == null) {
						System.out.println("검색된 테마가 없다.");
						continue;
					}
					themeDetail.remove(themeDetail.getMenuByIndex(1));
					themeDetail.add(makePlaceMenuGroup(input,myMapHandler));
					return;
				}
			}
		});
		return themeDetail;
	}
	
	Menu makePlaceMenuGroup(String title,MyMapHandler myMapHandler) {
		MenuGroup placeDetail = new MenuGroup(title+" 테마");
		Theme theme = myMapHandler.findByTitle(title);
		placeDetail.add(new Menu("장소 저장") {
			@Override
			public void execute() {
				myMapHandler.delete();
			}
		});
		placeDetail.add(new Menu("장소 목록") {
			@Override
			public void execute() {
				myMapHandler.delete();
			}
		});
		placeDetail.add(new Menu("장소 삭제") {
			@Override
			public void execute() {
				myMapHandler.delete();
			}
		});
		return placeDetail;
	}
}