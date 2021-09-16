package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class MyMapListHandler extends AbstractMyMapHandler{

	public void execute() {
		int index = 1;
		System.out.println("[테마 목록 조회]");
		if (AuthLoginHandler.getLoginUser().getThemeList().size() == 0) {
			System.out.println("등록된 테마가 없습니다.");
			return;
		}
		for (Theme list : AuthLoginHandler.getLoginUser().getThemeList()) {
			System.out.printf("(%d)\n", index++);
			System.out.printf("테마 제목 : %s\n", list.getTitle());
			System.out.printf("해시 태그 : %s\n", list.getHashtags().toString());
			System.out.printf("공개 여부 : %s\n", list.isPublic() ? "공개" : "비공개");
			System.out.println();
		}
	}
}
