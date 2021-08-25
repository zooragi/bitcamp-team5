package com.bitcamp.goodplace.Handler;

import java.util.ArrayList;

import com.bitcamp.goodplace.domain.Thema;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapHandler {
	User user;
	
	public MyMapHandler(User user) {
		this.user = user;
	}
	
	public void add() {
		Thema thema = new Thema();
		ArrayList<Thema> themaList = (ArrayList<Thema>) user.getThemaList();
		ArrayList<String> hashtagList = (ArrayList<String>) thema.getHashtag();
		System.out.println("나의 테마 등록하기");
		
		thema.setTitle(Prompt.inputString("테마 제목 : "));
		while(true) {
			String input = Prompt.inputString("해시 태그(완료: 빈 문자열) : ");
			if(input.length() == 0) break;
			
			if(hashtagList == null) {
				thema.setHashtag(new ArrayList<String>());
				hashtagList = (ArrayList<String>) thema.getHashtag();
			}
			hashtagList.add(input);
		}
		if(Prompt.inputString("공개 설정 하시겠습니까?(y/N) : ").equalsIgnoreCase("y")) {
			thema.setPublic(true);
		}
		if(themaList == null) {
			user.setThemaList(new ArrayList<Thema>());
			themaList = (ArrayList<Thema>) user.getThemaList();
		}
		themaList.add(thema);
	}
	
	public void list() {
		System.out.println("[테마 목록 조회]");
		if(user.getThemaList() == null) {
			System.out.println("등록된 테마가 없습니다.");
			return;
		}
		for(Thema list : user.getThemaList()) {
			System.out.printf("테마 제목 : %s\n",list.getTitle());
			System.out.printf("해시 태그 : %s\n",list.getHashtag().toString());
			System.out.printf("공개 여부 : %s\n",list.isPublic() ? "공개" : "비공개");
			System.out.println();
		}
		
		
	}
}
