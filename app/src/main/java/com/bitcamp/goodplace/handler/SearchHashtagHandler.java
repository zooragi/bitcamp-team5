package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchHashtagHandler extends AbstractSearchHandler {
  public SearchHashtagHandler(List<User> userList) {
    super(userList);
  }

  public void execute(CommandRequest request) {
    while (true) {
      System.out.println("[해시 태그 검색]");
      String input = Prompt.inputString("해시 태그를 입력하세요(취소 : 빈 문자열) :  ");
      if(input.length()==0) return;

      List<Theme> searchedTheme = new ArrayList<>();

      for (User user : userList) {
        for (Theme theme : user.getThemeList()) {
          for (String hashtag : theme.getHashtags()) {
            if (hashtag.contains(input) && theme.isPublic()) {
              searchedTheme.add(theme);
            }
          }
        }
      }

      if (searchedTheme.size() == 0) {
        System.out.println("해당하는 테마가 없습니다.");
        continue;
      }

      System.out.printf("[%s]의 검색결과\n", input);
      for (Theme list : searchedTheme) {
        System.out.printf("테마 제목 : %s\n", list.getTitle());
      }
      System.out.println();
    }
  }

}
