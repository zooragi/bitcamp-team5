package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class MyThemeListHandler extends AbstractMyMapHandler {

	public MyThemeListHandler(List<User> userList) {
		super(userList);
	}

	public void execute(CommandRequest request) {
		int index = 1;
		System.out.println("[테마 목록 조회]");
		if (AuthLoginHandler.getLoginUser().getThemeList().size() == 0) {
			System.out.println("등록된 테마가 없습니다.");
			return;
		}
		for (Theme list : AuthLoginHandler.getLoginUser().getThemeList()) {
			System.out.printf("(%d)\n", index++);
			System.out.printf("테마 제목 : %s\n", list.getTitle());
			System.out.printf("카테고리 : %s\n", list.getCategory());
			System.out.printf("해시 태그 : %s\n", list.getHashtags().toString());
			System.out.printf("공개 여부 : %s\n", list.isPublic() ? "공개" : "비공개");
			if (list.isPublic()) {
				System.out.printf("조회수 : %d\n", list.getViewCount());
			}
			System.out.println();
		}
	}
}
