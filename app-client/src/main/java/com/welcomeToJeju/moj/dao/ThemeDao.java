package com.welcomeToJeju.moj.dao;

import java.util.List;

import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {
	void insert(Theme theme) throws Exception;
	Theme findByTitle(String title) throws Exception;
	List<Theme> findByUserNo(int userNo) throws Exception;
	void update(Theme theme) throws Exception;
	public void delete(Theme theme) throws Exception;
//	public void hashtagInsert(String hashtag) throws Exception;
	List<Theme> hashtagSearch(String hashtagName) throws Exception;
	void likedThemeInsert(int themeNo,int userNo) throws Exception;
	void likedThemeDelete(int themeNo,int userNo) throws Exception;
	List<Theme> likedThemeList(int userNo) throws Exception;
	public List<Category> findAllCategory() throws Exception;
}
