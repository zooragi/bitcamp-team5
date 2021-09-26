package com.bitcamp.goodplace.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapDetailHandler extends AbstractMyMapHandler {
	Map<String,String> controlThemeMenu = new HashMap<>();
	
	public MyMapDetailHandler(){
		controlThemeMenu.put("pa", "/place/add");
		controlThemeMenu.put("pd", "/place/delete");
		controlThemeMenu.put("tu", "/myMap/update");
	}
	@Override
	public void execute(CommandRequest request) throws Exception {
		
		List<Theme> currentTheme = AuthLoginHandler.getLoginUser().getThemeList();
		Theme searchedTheme;
		String input;
		
		System.out.println("[테마 상세보기]");
		System.out.println();
		
		showThemeList();
		searchedTheme = chooseTheme();
		
		User user = AuthLoginHandler.getLoginUser();
		if (!user.getNickName().equals(searchedTheme.getUserName()) && user.getEmail().equals("root@test.com")) {
			return;
		}

		request.setAttrubute("searchedTheme", searchedTheme);
		
		while (true) {
			input =Prompt.inputString("지도 관리(1), 테마 관리(2), 이전 메뉴(0) ");
			System.out.println();
			if(Integer.parseInt(input) == 0) {
				return;
			} else if(Integer.parseInt(input) == 1) {
				showPlaceList(searchedTheme);
				input = Prompt.inputString("장소 추가(pa), 장소 삭제(pd), 이전메뉴(0)");
				if(input.equals("0")) return;
				request.getRequestDispatcher(controlThemeMenu.get(input.toLowerCase())).forword(request);
				return;
			} else if ( Integer.parseInt(input) == 2 ) {
				input = Prompt.inputString("테마 수정(tu), 이전메뉴(0)");
				if(input == "0") return;
				request.getRequestDispatcher(controlThemeMenu.get(input.toLowerCase())).forword(request);
				return;
			} else {
				System.out.println("명령어가 올바르지 않습니다!");
				continue;
			}
		}
		
	}
	private void showThemeList() {
		int index = 1;
		for(Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
			System.out.printf("(%d)\n", index++);
			System.out.printf("테마 제목 : %s\n", theme.getTitle());
			System.out.printf("카테고리 : %s\n", theme.getCategory());
			System.out.printf("해시 태그 : %s\n", theme.getHashtags().toString());
			System.out.printf("조회수 : %d\n", theme.getViewCount());
			System.out.println();
		}
	}
	private void showPlaceList(Theme searchedTheme) {
		int index = 1;
		for (Place placeList : searchedTheme.getPlaceList()) {
			System.out.printf("(%d)\n", index++);
			System.out.printf("장소 이름 > %s\n", placeList.getStoreName());
			System.out.printf("장소 주소 > %s\n", placeList.getAddressName());
			System.out.printf("위도 > %s\n", placeList.getxCoord());
			System.out.printf("경도 > %s\n", placeList.getyCoord());
			System.out.printf("장소 후기 > %s\n", placeList.getComment().toString());
			System.out.println();
		}
	}
	private Theme chooseTheme() {
		while(true) {
			try {
				int inputNum = Prompt.inputInt("번호를 입력하세요! ");
				if(inputNum > AuthLoginHandler.getLoginUser().getThemeList().size() || inputNum < 0) {
					System.out.println("무효한 번호 입니다");
					continue;
				}
				return AuthLoginHandler.getLoginUser().getThemeList().get(inputNum-1);
			}catch (Exception e) {
				System.out.println("------------------------------------------");
				System.out.printf("오류 발생: %s\n", e.getClass().getName());
				System.out.println("------------------------------------------");
			}
				
		}
	}
	
}
