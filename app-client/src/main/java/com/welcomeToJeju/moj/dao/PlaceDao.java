package com.welcomeToJeju.moj.dao;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeju.moj.domain.Place;

public interface PlaceDao {

  void insert(Place place) throws Exception;
  void insertPhoto(HashMap<String,Object> param) throws Exception;
  void insertComment(HashMap<String,Object> param) throws Exception;

  void delete(int placeNo) throws Exception;
  void deletePhoto(int placeNo) throws Exception;
  void deleteComment(int placeNo) throws Exception;

  List<Place> findAll() throws Exception;
  List<Place> findByThemeNo(int themeNo) throws Exception;
  //  List<Place> findByThemeTitle(String themeTitle) throws Exception;


}
