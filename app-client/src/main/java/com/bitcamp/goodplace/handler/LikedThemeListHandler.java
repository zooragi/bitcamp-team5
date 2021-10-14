package com.bitcamp.goodplace.handler;


import java.util.ArrayList;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;

public class LikedThemeListHandler implements Command{

  ThemeDao themeDao;

  public LikedThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아요 목록보기]");
    
    ArrayList<Theme> list = (ArrayList<Theme>) themeDao.likedThemeList(AuthLoginHandler.getLoginUser().getNickName());
    ThemeHandlerHelper.printList(list);
    
  }
}