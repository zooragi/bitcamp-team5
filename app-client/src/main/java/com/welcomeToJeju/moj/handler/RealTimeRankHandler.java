package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;

public class RealTimeRankHandler implements Command{
	
	ThemePrompt themePrompt;
	
  public RealTimeRankHandler(ThemePrompt themePrompt) {
  	this.themePrompt = themePrompt;
  }

  public void execute(CommandRequest request) throws Exception {
    int i = 1;
    System.out.println("[실시간 테마 순위 보기]");
    for(Theme theme : themePrompt.rank()) {
      System.out.printf("%d위. %s (조회수 : %d) > \n",i,theme.getTitle(),theme.getViewCount());
      i++;
    }
  }

}
