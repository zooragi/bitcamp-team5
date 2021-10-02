package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class MyThemeListHandler extends AbstractMyMapHandler {

  public MyThemeListHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {
    System.out.println("[테마 목록보기]");
    if (AuthLoginHandler.getLoginUser().getThemeList().size() == 0) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    for (Theme theme : AuthLoginHandler.getLoginUser().getThemeList()) {
      System.out.printf("<%d>\n", theme.getNo());
      System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
      System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
      System.out.printf("%s테마\n", theme.isPublic() ? "공개" : "비공개");
      if (theme.isPublic()) {
        System.out.printf("조회수 > %d\n", theme.getViewCount());
      }
      System.out.println();
    }
  }
}
