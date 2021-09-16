package com.bitcamp.goodplace.handler;

import java.util.ArrayList;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class FullThemeListHandler extends AbstractSearchHandler{

	public FullThemeListHandler(List<User> userList) {
		super(userList);
	}
	public void execute() {
		System.out.println("[전체 테마 목록]");
		int i = 1;
		for(User user : userList) {
			for(Theme theme : user.getThemeList()) {
				if(!theme.isPublic()) continue;
				System.out.printf("(%d)\n",i++);
				System.out.printf("[%s님의 테마]\n",theme.getUserName() );
				System.out.println("테마 이름 : " + theme.getTitle());
				System.out.println("해시 태그 : " + theme.getHashtags().toString());
			}
		}
	}

}
