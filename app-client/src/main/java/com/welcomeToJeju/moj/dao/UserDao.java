package com.welcomeToJeju.moj.dao;

import java.util.List;

import com.welcomeToJeju.moj.domain.User;

public interface UserDao {
	void insert(User user) throws Exception;
	User search(String name) throws Exception;
	void delete(int userNo) throws Exception;
	void update(User user) throws Exception;
	List<User> findAll() throws Exception;
	void userLikedUserInsert(int likedUser, int loginUserNo) throws Exception;
	void userLikedUserDelete(int likedUser, int loginUserNo) throws Exception;
	List<String> likedUserFindAll(int loginUserNo) throws Exception;
}
