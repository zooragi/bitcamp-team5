package com.bitcamp.goodplace.dao;

import java.util.List;

import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;

public interface ThemeDao {
	void insert(Theme theme) throws Exception;
	List<Theme> findAll() throws Exception;
	void update(Theme theme) throws Exception;
	String delete(String themeTitle) throws Exception;
	void placeInsert(Place place) throws Exception;
	String placeDelete(Place place) throws Exception;
	Theme selectedOne(Theme theme) throws Exception;
}
