package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Place;

public class MybatisPlaceDao implements PlaceDao{
  SqlSession sqlSession;

  public MybatisPlaceDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }
	
	@Override
	public void insert(Place place) throws Exception {
	}
	
	@Override
	public List<Place> findByThemeNo(int themeNo) throws Exception{
		return sqlSession.selectList("PlaceMapper.findByThemeNo",themeNo);
	}

}
