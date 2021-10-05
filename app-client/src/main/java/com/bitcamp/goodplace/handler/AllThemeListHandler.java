package com.bitcamp.goodplace.handler;

import java.util.ArrayList;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.request.RequestAgent;

public class AllThemeListHandler implements Command{

	RequestAgent requestAgent;

	public AllThemeListHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	public void execute(CommandRequest request) throws Exception {
		System.out.println("[전체 테마 목록보기]");
		requestAgent.request("theme.list", null);
		ArrayList<Theme> themeList = new ArrayList<>(requestAgent.getObjects(Theme.class));

		int i = 1;
		for (Theme theme : themeList) {
			if (!theme.isPublic())
				continue;
			System.out.printf("<%d>\n", i++);
			System.out.println("테마 이름 > " + theme.getTitle());
			System.out.println("해시 태그 > " + theme.getHashtags().toString());
		}
	}

}
