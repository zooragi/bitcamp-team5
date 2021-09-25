package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class SearchThemeHandler extends AbstractSearchHandler{

  public SearchThemeHandler(List<User> userList) {
    super(userList);
  }
  public void execute(CommandRequest request) {
    while(true) {
      String title = Prompt.inputString("테마를 검색하세요(취소 : 빈 문자열) : ");
      if(title.length() == 0) return;

      for(User user : userList) {
        for(Theme theme : user.getThemeList()) {
          if(theme.getTitle().contains(title)) {
            if(!theme.isPublic()) continue;
            System.out.printf("[%s]\n", theme.getTitle());
            System.out.printf("<%s 님의 테마>\n", theme.getUserName());
            System.out.printf("해시태그 : %s\n", theme.getHashtags().toString());
            System.out.printf("장소 : %s\n", theme.getPlaceList().toString());
            System.out.printf("조회수 : %d \n", theme.getViewCount() + 1);						
            theme.setViewCount(theme.getViewCount()+1);
            return;
          }
        }
      }
      System.out.println("해당 이름의 테마가 없습니다.");
    }
  }


}