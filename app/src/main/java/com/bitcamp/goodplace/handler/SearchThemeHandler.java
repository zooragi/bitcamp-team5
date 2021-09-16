package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchThemeHandler extends AbstractSearchHandler{
	
	public SearchThemeHandler(List<User> userList) {
		super(userList);
	}
	public void execute() {
		while(true) {
			String title = Prompt.inputString("테마를 검색하세요(취소 : 빈 문자열) : ");
			if(title.length() == 0) return;
			
			for(User user : userList) {
				for(Theme theme : user.getThemeList()) {
					if(theme.getTitle().equals(title)) {
						if(!theme.isPublic()) continue;
						System.out.printf("[%s]\n", theme.getTitle());
						System.out.println("유저 : " + theme.getUserName());
						System.out.println("해시 태그 : " + theme.getHashtags().toString());
						System.out.println("저장된 장소 : " + theme.getPlaceList().toString());
						System.out.println("조회수 : " + theme.getViewCount()+1);						
						theme.setViewCount(theme.getViewCount()+1);
						return;
					}
				}
			}
			System.out.println("해당 이름의 테마가 없습니다.");
		}
	}

	
}
