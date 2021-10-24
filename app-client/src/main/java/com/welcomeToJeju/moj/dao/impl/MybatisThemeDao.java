package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public class MybatisThemeDao implements ThemeDao{
	Connection con;
  SqlSession sqlSession;

  public MybatisThemeDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }
	
	@Override
	public void insert(Theme theme) throws Exception {
		sqlSession.insert("ThemeMapper.insert",theme);
		
		for(String hashtag : theme.getHashtags()) {
			HashMap<String,Object> params = new HashMap<>();
			params.put("themeNo", theme.getNo());
			params.put("name", hashtag);
			sqlSession.insert("ThemeMapper.insertHashtags", params);
		}
		sqlSession.commit();
	}

	@Override
	public List<Theme> findByUserNo(int userNo) throws Exception {
		return sqlSession.selectList("ThemeMapper.findByUserNo", userNo);
	}

	public List<Category> findAllCategory() throws Exception {
		return sqlSession.selectList("ThemeMapper.findAllCategory");
	}
	
	@Override
	public void update(Theme theme) throws Exception {
		sqlSession.update("ThemeMapper.update", theme);
		sqlSession.delete("ThemeMapper.hashtagDelete", theme.getNo());
		
		for(String hashtag : theme.getHashtags()) {
			HashMap<String,Object> params = new HashMap<>();
			params.put("themeNo", theme.getNo());
			params.put("name", hashtag);
			sqlSession.insert("ThemeMapper.insertHashtags", params);
		}
		sqlSession.commit();
	}

	@Override
	public void delete(Theme theme) throws Exception {
		HashMap<String, String> params = new HashMap<>();
	  params.put("themeNo",Integer.toString(theme.getNo()));
	  params.put("userNo",Integer.toString(theme.getOwner().getNo()));
	  
		sqlSession.delete("ThemeMapper.hashtagDelete", theme.getNo());
		System.out.println(params);
		sqlSession.delete("ThemeMapper.likedThemeAllDelete", theme.getNo());
		System.out.println(2);
		sqlSession.delete("ThemeMapper.delete", theme.getNo());
		sqlSession.commit();
	}

	@Override
	public List<Theme> hashtagSearch(String hashtagName) throws Exception {
		return sqlSession.selectList("ThemeMapper.hashtagSearch", hashtagName);
	}

	@Override
	public Theme findByTitle(String title) throws Exception {
		return sqlSession.selectOne("ThemeMapper.findByTitle", title);
	}
	
	@Override
	public List<Theme> likedThemeList(int userNo) throws Exception{
		return sqlSession.selectList("ThemeMapper.likedThemeList", userNo);
	}
	
	@Override
	public void likedThemeInsert(int themeNo, int userNo) throws Exception{
		HashMap<String, String> params = new HashMap<>();
	  params.put("themeNo",Integer.toString(themeNo));
	  params.put("userNo",Integer.toString(userNo));
		sqlSession.insert("ThemeMapper.likedThemeInsert", params);
		sqlSession.commit();
	}
	
	@Override
	public void likedThemeDelete(int themeNo,int userNo) throws Exception{
		HashMap<String, String> params = new HashMap<>();
	  params.put("themeNo",Integer.toString(themeNo));
	  params.put("userNo",Integer.toString(userNo));
	  sqlSession.insert("ThemeMapper.likedThemeDelete", params);
	  sqlSession.commit();
	}

}


