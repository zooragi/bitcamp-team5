package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class RealTimeRankHandler implements Command{
  List<User> userList;
  public RealTimeRankHandler(List<User> userList) {
    this.userList = userList;
  }

  public void execute(CommandRequest request) {
    int i = 1;
    System.out.println("[실시간 테마 랭킹]");
    for(Theme theme : rank()) {
      System.out.printf("%d위. %s (조회수 : %d)\n",i,theme.getTitle(),theme.getViewCount());
      i++;
    }
  }

  public List<Theme> rank() {
    List<Theme> themeList = new ArrayList<>();
    for(User user : userList) {
      for(Theme theme : user.getThemeList()) {
        if(theme.isPublic() == true) themeList.add(theme);
      }
    }
    Collections.sort(themeList);
    return themeList;
  }

}
