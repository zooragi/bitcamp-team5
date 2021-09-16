package com.bitcamp.goodplace.handler;

import java.util.ArrayList;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchUserHandler extends AbstractSearchHandler{
	public SearchUserHandler(List<User> userList) {
		super(userList);
	}
	public void execute() {
		System.out.println("[유저 검색]");
		while(true) {
			String input = Prompt.inputString("닉네임을 입력하세요(취소 : 빈 문자열) : ");
			if(input.length()==0) return;
			for(User user : userList) {
				if(user.getNickName().equals(input)) {
					System.out.printf("[%s]유저의 테마 목록\n", user.getNickName());
					user.setViewCount(user.getViewCount()+1);
					for(Theme theme : user.getThemeList()) {
						if(!theme.isPublic()) continue;
						System.out.println("테마 제목 : " + theme.getTitle());
					}
					return;
				}
			}
			System.out.println("해당 이름의 유저가 없습니다.");
		}

		

	}

}
