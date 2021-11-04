package com.welcomeToJeju.moj.handler.search;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.PlaceHandlerHelper;
import com.welcomeToJeju.util.Prompt;

public class SearchThemeHandler implements Command {

  ThemeDao themeDao;
  PlaceDao placeDao;
  SqlSession sqlSession;

  public SearchThemeHandler(ThemeDao themeDao, PlaceDao placeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.placeDao = placeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 검색하기]");

    while (true) {
      String input = Prompt.inputString("테마(취소 : 엔터) > ");

      if(input.equals("") || input.length() == 0) {
        System.out.println("테마 검색하기 취소!");
        return;
      }

      ArrayList<Theme> list = (ArrayList<Theme>) themeDao.findByKeyword(input);

      if(list.size() == 0) {
        System.out.println("테마 없음!");
        continue;
      }

      Theme theme = promptSearchedThemeList(list);
      
      
      System.out.printf("[%s] 검색 결과\n", input);
      
      PlaceHandlerHelper.printPlaceInfo((ArrayList<Place>) placeDao.findByThemeNo(theme.getNo()));
      
      // 조회수 +1
      int viewCount = theme.getViewCount();
      HashMap<String,Object> params = new HashMap<>();
      params.put("themeNo", theme.getNo());
      params.put("viewCnt", viewCount + 1);
      themeDao.updateViewCount(params);
      sqlSession.commit();

      return;
    }
  }
  
  private Theme promptSearchedThemeList(ArrayList<Theme> list) {
  	int i = 1 ;
  	int selectNo = 0;
  	for(Theme t : list) {
  		System.out.printf("%d. %s\n",i ,t.getTitle());
  	}
  	while(true) {
  		selectNo = Prompt.inputInt("번호 입력 : ");
  		if(selectNo > list.size() || selectNo < 0) {
  			System.out.println("잘못된 번호, 다시 입력!!");
  			continue;
  		}
  		break;
  	}
  	
  	return list.get(selectNo-1);
  }
}
