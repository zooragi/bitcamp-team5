package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserUpdateHandler extends AbstractUserHandler{
	public UserUpdateHandler(List<User> user) {
		super(user);
	}

	public void execute() {
		System.out.println("[회원 변경]");
		int no = Prompt.inputInt("번호? ");

		User user = findByNo(no);

		if (user == null) {
			System.out.println("해당 번호의 회원이 없습니다.");
			return;
		}

		String name = Prompt.inputString("이름(" + user.getName() + ")? ");
		String email = Prompt.inputString("이메일(" + user.getEmail() + ")? ");
		String password = Prompt.inputString("암호? ");
		String tel = Prompt.inputString("전화(" + user.getTel() + ")? ");
		String nickName = Prompt.inputString("닉네임(" + user.getNickName() + ")? ");

		String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("회원 변경을 취소하였습니다.");
			return;
		}

		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(tel);
		user.setNickName(nickName);

		System.out.println("회원을 변경하였습니다.");
	}

}
