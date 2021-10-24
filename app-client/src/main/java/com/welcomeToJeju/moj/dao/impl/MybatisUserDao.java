package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

public class MybatisUserDao implements UserDao{
	
	Connection con;
  SqlSession sqlSession;

  public MybatisUserDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }
  
	@Override
	public void insert(User user) throws Exception {
		sqlSession.insert("UserMapper.insert",user);
		sqlSession.commit();
	}

	@Override
	public List<User> findAll() throws Exception {
		return sqlSession.selectList("UserMapper.findAll");
	}
	
	@Override
	public User selectOneByEmailPassword(String email, String password) throws Exception {
		HashMap<String,String> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		return sqlSession.selectOne("UserMapper.selectOneByEmailPassword", params);
	}

	@Override
  public void delete(int userNo) throws Exception {
		sqlSession.delete("UserMapper.userLikedUserAllDelete", userNo);
		sqlSession.delete("UserMapper.delete", userNo);
		sqlSession.commit();
  }
	
	@Override
	public void update(User user) throws Exception {
		sqlSession.update("UserMapper.update", user);
		sqlSession.commit();
	}
	
	@Override
	public void viewCountUpdate(int userNo, int viewCnt) throws Exception{
		HashMap<String,Object> params = new HashMap<>();
		params.put("userNo", userNo);
		params.put("viewCnt", viewCnt);
		sqlSession.update("UserMapper.viewCountUpdate", params);
		sqlSession.commit();
	}
	
	@Override
	public void warnedCountUpdate(int userNo, int warnedCnt) throws Exception{
		HashMap<String,Object> params = new HashMap<>();
		params.put("userNo", userNo);
		params.put("warnedCnt", warnedCnt);
		sqlSession.update("UserMapper.warnedCountUpdate", params);
		sqlSession.commit();
	}
	
	@Override
	public void reportedCountUpdate(int userNo, int reportedCnt) throws Exception{
		HashMap<String,Object> params = new HashMap<>();
		params.put("userNo", userNo);
		params.put("reportedCnt", reportedCnt);
		sqlSession.update("UserMapper.reportedCountUpdate", params);
		sqlSession.commit();
	}
	
	@Override
	public User findByName(String nickname) throws Exception {
		return sqlSession.selectOne("UserMapper.findByName", nickname);
	}
	
  @Override
  public void userLikedUserInsert(int likedUserNo, int loginUserNo) throws Exception {
		HashMap<String,Integer> params = new HashMap<>();
		params.put("likedUserNo", likedUserNo);
		params.put("loginUserNo", loginUserNo);
		sqlSession.insert("UserMapper.userLikedUserInsert", params);
		sqlSession.commit();
  }	
	
  @Override
  public List<User> likedUserFindAll(int loginUserNo) throws Exception {
  	return sqlSession.selectList("UserMapper.likedUserFindAll", loginUserNo);
  }
  
  @Override
  public void userLikedUserDelete(int likedUserNo, int loginUserNo) throws Exception {
		HashMap<String,Integer> params = new HashMap<>();
		params.put("likedUserNo", likedUserNo);
		params.put("loginUserNo", loginUserNo);
		sqlSession.delete("UserMapper.userLikedUserDelete", params);
		sqlSession.commit();
}
  
	@Override
	public User findByNo(int userNo) throws Exception {
		return sqlSession.selectOne("UserMapper.findByNo", userNo);
	}

}
