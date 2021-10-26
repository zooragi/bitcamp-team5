package com.welcomeToJeju.moj.dao;

import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {
	void insert(Theme theme) throws Exception;
	void insertHashtags(HashMap<String,Object> params) throws Exception;
	List<Theme> findAll() throws Exception;
	Theme findByTitle(String title) throws Exception;
	List<Theme> findByUserNo(int userNo) throws Exception;
	void update(Theme theme) throws Exception;
	void viewCountUpdate(HashMap<String,Object> params) throws Exception;
	void reportedCountUpdate(HashMap<String,Object> params) throws Exception;
	public void delete(int themeNo) throws Exception;
	public void likedThemeAllDelete(int themeNo) throws Exception;
	public void hashtagDelete(int themeNo) throws Exception;
	List<Theme> hashtagSearch(String hashtagName) throws Exception;
	void likedThemeInsert(HashMap<String, String> params) throws Exception;
	void likedThemeDelete(HashMap<String, String> params) throws Exception;
	List<Theme> likedThemeList(int userNo) throws Exception;
	public List<Category> findAllCategory() throws Exception;
}
