package com.bitcamp.goodplace.dao;

import java.util.List;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;

public interface ThemeDao {
	void insert(Theme theme) throws Exception;
	Theme search(String title) throws Exception;
	List<Theme> findAll() throws Exception;
	void update(Theme theme) throws Exception;
	String delete(String themeTitle) throws Exception;
	void placeInsert(Place place) throws Exception;
	String placeDelete(Place place) throws Exception;
	List<Theme> hashtagSearch(String hashtagName) throws Exception;
	void likedThemeInsert(String themeTitle,String userName) throws Exception;
	void likedThemeDelete(String themeTitle,String userName) throws Exception;
	List<Theme> likedThemeList(String userName) throws Exception;
}
