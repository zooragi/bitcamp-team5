package com.bitcamp.goodplace.Handler;

import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class AuthLogoutHandler implements Command{

	public void execute() {
	    System.out.println("[로그아웃]");

	    AuthLoginHandler.loginUser = null;
	    System.out.println("로그아웃 하였습니다.");
	}

}
