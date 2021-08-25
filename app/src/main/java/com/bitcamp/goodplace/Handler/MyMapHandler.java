package com.bitcamp.goodplace.Handler;

import java.util.ArrayList;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapHandler {
	User user;
	
	public MyMapHandler(User user) {
		this.user = user;
	}
	
	public void add() {
		Theme thema = new Theme();
		ArrayList<Theme> themaList = (ArrayList<Theme>) user.getThemeList();
		ArrayList<String> hashtagList = (ArrayList<String>) thema.getHashtags();
		System.out.println("나의 테마 등록하기");
		
		thema.setTitle(Prompt.inputString("테마 제목 : "));
		while(true) {
			String input = Prompt.inputString("해시 태그(완료: 빈 문자열) : ");
			if(input.length() == 0) break;
			
			hashtagList.add(input);
		}
		if(Prompt.inputString("공개 설정 하시겠습니까?(y/N) : ").equalsIgnoreCase("y")) {
			thema.setPublic(true);
		}
		themaList.add(thema);
	}
	
	public void list() {
		int index = 1;
		System.out.println("[테마 목록 조회]");
		if(user.getThemeList() == null) {
			System.out.println("등록된 테마가 없습니다.");
			return;
		}
		for(Theme list : user.getThemeList()) {
			System.out.printf("(%d)\n",index++);
			System.out.printf("테마 제목 : %s\n",list.getTitle());
			System.out.printf("해시 태그 : %s\n",list.getHashtags().toString());
			System.out.printf("공개 여부 : %s\n",list.isPublic() ? "공개" : "비공개");
			System.out.println();
		}
		
		
	}
}
