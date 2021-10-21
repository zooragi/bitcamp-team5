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
	public User selectOneByEmailPassword(String email, String password) throws Exception {
		HashMap<String,String> params = new HashMap<>();
		params.put("email", email);
		params.put("password", password);
		return sqlSession.selectOne("UserMapper.selectOneByEmailPassword", params);
	}

	@Override
  public void delete(int userNo) throws Exception {
		sqlSession.delete("UserMapper.delete", userNo);
		sqlSession.commit();
  }
	
	@Override
	public User findByNo(int userNo) throws Exception {
//		try(PreparedStatement stmt = con.prepareStatement(
//				"select email, nickname, created_dt from jeju_user where user_no=" + userNo)){
//			try(ResultSet rs = stmt.executeQuery()){
//				if(!rs.next()) {
//					return null;
//				}
//				User user = new User();
//				user.setEmail(rs.getString("email"));
//				user.setNickName(rs.getString("nickname"));
//				user.setRegisteredDate(rs.getDate("created_dt"));
//				return user;
//			}
//		}
		return null;
	}
	
	@Override
	public User findByName(String nickname) throws Exception {
//		try(PreparedStatement stmt = con.prepareStatement(
//				"select user_no, email, nickname, created_dt from jeju_user where nickname=?")){
//			stmt.setString(1, nickname);
//			try(ResultSet rs = stmt.executeQuery()){
//				if(!rs.next()) {
//					return null;
//				}
//				User user = new User();
//				user.setNo(rs.getInt("user_no"));
//				user.setEmail(rs.getString("email"));
//				user.setNickName(rs.getString("nickname"));
//				user.setRegisteredDate(rs.getDate("created_dt"));
//				return user;
//			}
//		}
		return null;
	}
	
	@Override
	public void update(User user) throws Exception {
//		try(PreparedStatement stmt = con.prepareStatement(
//				"update jeju_user set email=?, password=password(?), nickname=? where user_no="+user.getNo())){
//			
//			stmt.setString(1, user.getEmail());
//			stmt.setString(2, user.getPassword());
//			stmt.setString(3, user.getNickName());
//			
//			if(stmt.executeUpdate() == 0) {
//				throw new Exception("회원 데이터 변경 실패!");
//			}
//		}
	}

	
	
	@Override
	public List<User> findAll() throws Exception {
		return sqlSession.selectList("UserMapper.findAll");
	}
	
  @Override
  public void userLikedUserInsert(int likedUserNo, int loginUserNo) throws Exception {
//		try(PreparedStatement stmt = con.prepareStatement(
//				"Insert into jeju_liked_user(user_no,user_no2) values(?,?)")){
//			stmt.setInt(1, loginUserNo);
//			stmt.setInt(2, likedUserNo);
//			if(stmt.executeUpdate() == 0) {
//				throw new Exception("회원 데이터 저장 실패!");
//			}
//		}
  }	
  
  public void userLikedUserDelete(int likedUserNo, int loginUserNo) throws Exception {
//		try(PreparedStatement stmt = con.prepareStatement(
//				"delete from jeju_liked_user where user_no=? and user_no2=?")){
//			stmt.setInt(1, loginUserNo);
//			stmt.setInt(2, likedUserNo);
//			
//  		if(stmt.executeUpdate() == 0) {
//  			throw new Exception("회원 데이터 삭제 실패!");
//  		}
//		}
  }
  
  @Override
  public List<User> likedUserFindAll(int loginUserNo) throws Exception {
//  	try(PreparedStatement stmt = con.prepareStatement(
//  			"select" 
//  			+" ju.user_no,"
//  			+" email,"
//  			+" nickname,"
//  			+" created_dt" 
//  			+" from jeju_user ju"
//  			+" join (select jlu.user_no2 from jeju_liked_user jlu where user_no=?) jlu"
//  			+" on jlu.user_no2 = ju.user_no;")){
//  		
//  		stmt.setInt(1, loginUserNo);
//  		
//  		ArrayList<User> list = new ArrayList<>();
//  		
//			try(ResultSet rs = stmt.executeQuery()){
//				while(rs.next()) {
//					User user = new User();
//					user.setNo(rs.getInt("user_no"));
//					user.setEmail(rs.getString("email"));
//					user.setNickName(rs.getString("nickname"));
//					user.setRegisteredDate(rs.getDate("created_dt"));
//					list.add(user);
//				}
//			}
//			return list;
//		}
  	return null;
  }

}
