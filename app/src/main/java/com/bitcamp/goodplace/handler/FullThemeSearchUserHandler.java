package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class FullThemeSearchUserHandler extends AbstractFullThemeHandler{
	public FullThemeSearchUserHandler(List<User> userList) {
		super(userList);
	}
	public void execute() {
		System.out.println("[유저 검색]");

		String input = Prompt.inputString("닉네임을 입력하세요> ");
		for(User user : userList) {
			if(user.getNickName().equals(input)) {
				System.out.printf("[%s]유저의 테마 목록\n", user.getNickName());
				for(Theme theme : user.getThemeList()) {
					if(!theme.isPublic()) continue;
					System.out.println("테마 제목 : " + theme.getTitle());
				}
			}
		}

	}

}
