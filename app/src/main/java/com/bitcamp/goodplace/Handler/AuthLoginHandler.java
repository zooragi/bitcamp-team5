package com.bitcamp.goodplace.Handler;

import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class AuthLoginHandler implements Command{

	List<User> userList;
	static User loginUser;
	public static User getLoginUser() {
		return loginUser;
	}
	
	public AuthLoginHandler(List<User> userList) {
		this.userList = userList;
	}

	public void execute() {
		System.out.println("[로그인]");

		String email = Prompt.inputString("이메일? ");
		String password = Prompt.inputString("암호?");

		User user = findByEmailPassword(email, password);

		if (user == null) {
			System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
		} else {
			System.out.printf("%s님 환영합니다!\n", user.getName());
		}
		loginUser = user;
	}

	private User findByEmailPassword(String email, String password) {
		for (User user : userList) {
			if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
}
