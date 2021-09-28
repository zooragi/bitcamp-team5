package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public abstract class AbstractBookmarkHandler implements Command {

  List<User> userList;

  public AbstractBookmarkHandler(List<User> userList) {
    this.userList = userList;
  }


  protected Theme findByTheme(String themeTitle) {
    for(User user : userList) {
      for (Theme theme : user.getThemeList()) {
        if (theme.getTitle().equals(themeTitle)) {
          return theme;
        }
      }
    }
    return null;
  }
}
