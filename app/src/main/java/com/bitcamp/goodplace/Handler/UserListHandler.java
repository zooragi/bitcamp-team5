package com.bitcamp.goodplace.Handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserListHandler extends AbstractUserHandler{
	public UserListHandler(List<User> userList) {
		super(userList);
	}
	public void execute() {
		System.out.println("[회원 목록]");

		for (User user : userList) {
			System.out.printf("회원 번호 : %s\n", user.getNo());
			System.out.printf("회원 이름 : %s\n", user.getName());
			System.out.println();
		}
	}
}
