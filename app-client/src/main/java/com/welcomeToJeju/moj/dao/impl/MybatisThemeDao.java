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
//		try(PreparedStatement stmt = con.prepareStatement(
//				"update jeju_theme set title=?, public=?, category=? where theme_no="+theme.getNo())){
//			
//			stmt.setString(1, theme.getTitle());
//			stmt.setInt(2, theme.isPublic());
//			stmt.setString(3, theme.getCategory());
//			
//			if(stmt.executeUpdate() == 0) {
//				throw new Exception("회원 데이터 변경 실패!");
//			}
//		}
	}

	@Override
	public void delete(Theme theme) throws Exception {
		sqlSession.delete("ThemeMapper.hashtagDelete", theme.getNo());
		sqlSession.delete("ThemeMapper.delete", theme.getNo());
		sqlSession.commit();
	}

	@Override
	public Theme search(String title) throws Exception {
//		requestAgent.request("theme.search", title);
//    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//      throw new Exception(requestAgent.getObject(String.class));
//    }
//		return requestAgent.getObject(Theme.class);
		return null;
	}

	@Override
	public List<Theme> hashtagSearch(String hashtagName) throws Exception {
//		requestAgent.request("theme.hashtag.search", hashtagName);
//    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//      throw new Exception(requestAgent.getObject(String.class));
//    }
//		
//		return new ArrayList<>(requestAgent.getObjects(Theme.class));
		return null;
	}
	
	@Override
	public void likedThemeInsert(int themeNo, int userNo) throws Exception{
//		HashMap<String, String> params = new HashMap<>();
//	  params.put("themeNo",Integer.toString(themeNo));
//	  params.put("userNo",Integer.toString(userNo));
//	  requestAgent.request("theme.liked.insert", params);
//		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//			throw new Exception(requestAgent.getObject(String.class));
//		}
		
	}
	
	@Override
	public void likedThemeDelete(int themeNo,int userNo) throws Exception{
//		HashMap<String, String> params = new HashMap<>();
//	  params.put("themeNo",Integer.toString(themeNo));
//	  params.put("userNo",Integer.toString(userNo));
//	  requestAgent.request("theme.liked.delete", params);
//		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//			throw new Exception(requestAgent.getObject(String.class));
//		}
	}
	
	@Override
	public List<Theme> likedThemeList(int userNo) throws Exception{
//		requestAgent.request("theme.liked.list", Integer.toString(userNo));
//		
//    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//      throw new Exception(requestAgent.getObject(String.class));
//    }
//
//    return new ArrayList<>(requestAgent.getObjects(Theme.class));
//	}
	return null;
	}
}
