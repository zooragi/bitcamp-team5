package com.welcomeToJeju.moj.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Comment;
import com.welcomeToJeju.moj.domain.Photo;
import com.welcomeToJeju.moj.domain.Place;

public class MybatisPlaceDao implements PlaceDao{
  SqlSession sqlSession;

  public MybatisPlaceDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }
	
	@Override
	public void insert(Place place) throws Exception {
		sqlSession.insert("PlaceMapper.insert",place);
		HashMap<String,Object> param1 = new HashMap<>();
		HashMap<String,Object> param2 = new HashMap<>();
		
		for(Comment comment : place.getComments()) {
			param1.put("placeNo", place.getNo());
			param1.put("userNo", place.getOwner().getNo());
			param1.put("comment", comment.getComment());
			sqlSession.insert("PlaceMapper.commentInsert",param1);
		}
		
		
		for(Photo photo : place.getPhotos()) {
			param2.put("placeNo", place.getNo());
			param2.put("userNo", place.getOwner().getNo());
			param2.put("filePath", photo.getFilePath());
			sqlSession.insert("PlaceMapper.photoInsert",param2);
		}
		
		
		sqlSession.commit();
	}
	
	@Override
	public List<Place> findByThemeNo(int themeNo) throws Exception {
		return sqlSession.selectList("PlaceMapper.findByThemeNo",themeNo);
	}

	public void delete(Place place) throws Exception {
		sqlSession.delete("PlaceMapper.deletePhoto", place.getNo());
		sqlSession.delete("PlaceMapper.deleteComment", place.getNo());
		sqlSession.delete("PlaceMapper.delete", place.getNo());
	}
}
