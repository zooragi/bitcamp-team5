package com.welcomeToJeju.moj.dao;

import java.util.HashMap;
import java.util.List;

import com.welcomeToJeju.moj.domain.User;

public interface UserDao {
	void insert(User user) throws Exception;
	User findByNo(int userNo) throws Exception;
	User findByName(String nickname) throws Exception;
	void delete(int userNo) throws Exception;
	void update(User user) throws Exception;
	void viewCountUpdate(HashMap<String,Object> params) throws Exception;
	void reportedCountUpdate(HashMap<String,Object> params) throws Exception;
	void warnedCountUpdate(HashMap<String,Object> params) throws Exception;
	List<User> findAll() throws Exception;
	User selectOneByEmailPassword(HashMap<String,String> params) throws Exception;
	void userLikedUserInsert(HashMap<String,Integer> params) throws Exception;
	void userLikedUserDelete(HashMap<String,Integer> params) throws Exception;
	void userLikedUserAllDelete(int userNo) throws Exception;
	List<User> likedUserFindAll(int loginUserNo) throws Exception;
	
}
