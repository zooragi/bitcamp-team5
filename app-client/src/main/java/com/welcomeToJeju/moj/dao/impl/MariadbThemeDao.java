package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.request.RequestAgent;

public class MariadbThemeDao implements ThemeDao{
	Connection con;
	
	public MariadbThemeDao(Connection con) {
		this.con = con;
	}
	
	@Override
	public void insert(Theme theme) throws Exception {
		try(PreparedStatement stmt = con.prepareStatement(
				"Insert into jeju_theme(user_no,title,public,category) values(?,?,?,?)")){
			stmt.setInt(1, theme.getThemeOwnerNo());
			stmt.setString(2, theme.getTitle());
			stmt.setInt(3, theme.isPublic());
			stmt.setString(4, theme.getCategory());
			
			if(stmt.executeUpdate() == 0) {
				throw new Exception("회원 데이터 저장 실패!");
			}
		}
		
	}
	
//	@Override
//	public void hashtagInsert(Theme) throws Exception{
//		try(PreparedStatement stmt = con.prepareStatement(
//				"Insert into jeju_hashtag(hashtag_no,theme_no,name) values(?,?,?)")){
//			
//		}
//	}
	
	@Override
	public List<Theme> findByUserNo(int userNo) throws Exception {
		try(PreparedStatement stmt = con.prepareStatement(
				"select theme_no, user_no, title, public, category from jeju_theme where user_no="+userNo);
				ResultSet rs = stmt.executeQuery()){
			ArrayList<Theme> list = new ArrayList<>();
			
			while(rs.next()) {
				Theme theme = new Theme();
				
				theme.setNo(rs.getInt("theme_no"));
				theme.setThemeOwnerNo(rs.getInt("user_no"));
				theme.setTitle(rs.getString("title"));
				theme.setPublic(rs.getInt("public"));
				theme.setCategory(rs.getString("category"));
				
				list.add(theme);
			}
			return list;
		}
	}

	@Override
	public void update(Theme theme) throws Exception {
		try(PreparedStatement stmt = con.prepareStatement(
				"update jeju_theme set title=?, public=?, category=? where theme_no="+theme.getNo())){
			
			stmt.setString(1, theme.getTitle());
			stmt.setInt(2, theme.isPublic());
			stmt.setString(3, theme.getCategory());
			
			if(stmt.executeUpdate() == 0) {
				throw new Exception("회원 데이터 변경 실패!");
			}
		}
	}

	@Override
	public String delete(Theme theme) throws Exception {
		try(PreparedStatement stmt = con.prepareStatement(
				"delete from jeju_theme where theme_no="+theme.getNo())){
  		if(stmt.executeUpdate() == 0) {
  			throw new Exception("회원 데이터 삭제 실패!");
  		}
		}
		return theme.getTitle();
	}

	@Override
	public void placeInsert(Place place) throws Exception {
//		requestAgent.request("theme.place.insert", place);
//		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//			throw new Exception(requestAgent.getObject(String.class));
//		}
	}

	@Override
	public String placeDelete(Place place) throws Exception {
//		requestAgent.request("theme.place.delete", place);
//    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//      throw new Exception(requestAgent.getObject(String.class));
//    }
//		return requestAgent.getObject(String.class);
		return null;
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
