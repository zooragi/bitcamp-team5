package com.welcomeToJeju.moj.handler.likedTheme;


import java.util.Collection;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;

public class LikedThemeListHandler implements Command{

  ThemeDao themeDao;

  public LikedThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 좋아요 목록 보기]");

    Collection<Theme> themeList = themeDao.findAllLikedTheme(AuthLoginHandler.getLoginUser().getNo());

    // 닉네임 안 됨
    int no = 1;
    for (Theme theme : themeList) {
      System.out.printf("<%d> '%s' 님의 '%s' 테마\n",
          no++, theme.getOwner().getNickname(), theme.getTitle());
    }
  }


}
