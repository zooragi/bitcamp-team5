package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public abstract class AbstactLikedThemeHandler implements Command {

  List<User> userList;

  public AbstactLikedThemeHandler(List<User> userList) {
    this.userList = userList;
  }

  protected Theme findByTitle(String title) {
    for (User user : userList) {
      for (Theme theme : user.getThemeList()) {
        if (theme.getTitle().equals(title)) {
          return theme;
        }
      }
    }
    return null;
  }


}