package com.welcomeToJeju.moj.dao;

import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

public interface ThemeDao {
	void insert(Theme theme) throws Exception;
	void insertHashtags(HashMap<String,Object> params) throws Exception;
	List<Theme> findAll() throws Exception;
	Theme findByTitle(String title) throws Exception;
	List<Theme> findByUserNo(int userNo) throws Exception;
	void update(Theme theme) throws Exception;
	void updateViewCount(HashMap<String,Object> params) throws Exception;
	void updateReportedCount(HashMap<String,Object> params) throws Exception;
	public void delete(int themeNo) throws Exception;
	public void deleteAllLikedTheme(int themeNo) throws Exception;
	public void deleteHashtag(int themeNo) throws Exception;
	List<Theme> hashtagSearch(String hashtagName) throws Exception;
	void insertLikedTheme(HashMap<String, String> params) throws Exception;
	void deleteLikedTheme(HashMap<String, String> params) throws Exception;
	List<Theme> likedThemeList(int userNo) throws Exception;
	List<Category> findAllCategory() throws Exception;
	List<Theme> sortThemeByViewCount() throws Exception;
	List<Theme> bringReportedUser() throws Exception;
}
