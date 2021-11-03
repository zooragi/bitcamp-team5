package com.welcomeToJeju.dao;

import java.util.HashMap;

import java.util.List;

import com.welcomeToJeju.domain.User;

public interface UserDao {
	void insert(User user) throws Exception;
	User findByNo(int userNo) throws Exception;
	User findByName(String nickname) throws Exception;
	void delete(int userNo) throws Exception;
	void update(User user) throws Exception;
	void updateViewCount(HashMap<String,Object> params) throws Exception;
	void updateReportedCount(HashMap<String,Object> params) throws Exception;
	void updateWarnedCount(HashMap<String,Object> params) throws Exception;
	List<User> findAll() throws Exception;
	User findByEmailAndPassword(HashMap<String,String> params) throws Exception;
	void insertLikedUser(HashMap<String,Integer> params) throws Exception;
	void deleteLikedUser(HashMap<String,Integer> params) throws Exception;
	void deleteAllLikedUser(int userNo) throws Exception;
	List<User> findAllLikedUser(int loginUserNo) throws Exception;
	List<User> sortUserByViewCount() throws Exception;
	List<User> bringReportedUser() throws Exception;
}
