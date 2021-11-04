package com.welcomeToJeju.moj.handler.theme;

import java.util.List;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.util.Prompt;

public class ThemeHandlerHelper {

  ThemeDao themeDao;

  public ThemeHandlerHelper(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  public Category promptCategory() throws Exception {
    return promptCategory(-1);
  }

  public Category promptCategory(int categoryNo) throws Exception {
    List<Category> categoryList = themeDao.findAllCategory();

    for (Category category : categoryList) {
      System.out.printf("%d. %s ", category.getNo(), category.getName());
    }

    System.out.println();

    return categoryList.get(Prompt.inputInt("카테고리 > ") - 1);
  }


}
