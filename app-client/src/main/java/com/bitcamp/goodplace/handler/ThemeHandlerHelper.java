package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.Theme;

public class ThemeHandlerHelper {

	public static void printList(List<Theme> themeList) {
		for(Theme theme : themeList) {
				System.out.printf("테마명 > %s\n",theme.getTitle());
		}
	}
}
