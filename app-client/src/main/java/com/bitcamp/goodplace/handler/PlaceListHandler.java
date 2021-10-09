package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.dao.ThemeDao;
import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;

public class PlaceListHandler implements Command {

	ThemeDao themeDao;

	public PlaceListHandler(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[장소 목록보기]");

		Theme theme = (Theme) request.getAttribute("theme");

		System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());

		PlaceHandlerHelper.printPlaceInfo(theme);

	}

}