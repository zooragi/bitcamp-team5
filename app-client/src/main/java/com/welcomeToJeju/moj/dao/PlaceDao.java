package com.welcomeToJeju.moj.dao;

import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.domain.Place;

public interface PlaceDao {
	void insert(Place place) throws Exception;
	void commentInsert(HashMap<String,Object> param) throws Exception;
	void photoInsert(HashMap<String,Object> param) throws Exception;
	List<Place> findByThemeNo(int themeNo) throws Exception;
	void delete(int placeNo) throws Exception;
	void commentDelete(int placeNo) throws Exception;
	void photoDelete(int placeNo) throws Exception;
}
