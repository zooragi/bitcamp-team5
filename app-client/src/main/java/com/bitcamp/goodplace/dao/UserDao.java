package com.bitcamp.goodplace.dao;

import java.util.List;

import com.bitcamp.goodplace.domain.User;

public interface UserDao {
	void insert(User user) throws Exception;
	User search(String name) throws Exception;
	void update(User user) throws Exception;
	List<User> findAll() throws Exception;
	void userLikedUserInsert(String likedUser, String loginUser) throws Exception;
	void userLikedUserDelete(String likedUser, String loginUser) throws Exception;
	List<String> likedUserFindAll(User loginUser) throws Exception;
}
