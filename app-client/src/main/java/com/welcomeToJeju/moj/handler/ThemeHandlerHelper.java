package com.welcomeToJeju.moj.handler;

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

		System.out.println("[카테고리]");
		while (true) {
			int i = 0;
			for (Category c : categoryList) {
				System.out.printf("%d.%s\n", ++i, c.getName());
			}
			int no = Prompt.inputInt("번호 입력 > ");
			if (no >= 1 && no <= categoryList.size()) {
				return categoryList.get(no - 1);
			}
			System.out.println("잘못된 번호");
		}
	}
}
