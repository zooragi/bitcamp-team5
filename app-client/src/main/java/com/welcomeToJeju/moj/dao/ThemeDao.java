package com.welcomeToJeju.moj.dao;

import java.util.List;

import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {
	void insert(Theme theme) throws Exception;
	Theme search(String title) throws Exception;
	List<Theme> findAll() throws Exception;
	void update(Theme theme) throws Exception;
	String delete(String themeTitle) throws Exception;
	void placeInsert(Place place) throws Exception;
	String placeDelete(Place place) throws Exception;
	List<Theme> hashtagSearch(String hashtagName) throws Exception;
	void likedThemeInsert(int themeNo,int userNo) throws Exception;
	void likedThemeDelete(int themeNo,int userNo) throws Exception;
	List<Theme> likedThemeList(int userNo) throws Exception;
}
