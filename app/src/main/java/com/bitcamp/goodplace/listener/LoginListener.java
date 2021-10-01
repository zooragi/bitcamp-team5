package com.bitcamp.goodplace.listener;

import java.util.Map;

import com.bitcamp.context.ApplicationContextListener;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.goodplace.handler.AuthLoginHandler;

public class LoginListener implements ApplicationContextListener{

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(Map<String, Object> params) {
		User currentUser = AuthLoginHandler.getLoginUser();
    System.out.println("****************************************");
    System.out.printf("* %s님 환영합니다     *\n",currentUser.getNickName());
    System.out.println("****************************************");
		if(currentUser.getWarningCount() > 2) {
			System.out.printf("-------------경고 %d회 누적입니다.-------------",currentUser.getWarningCount());
			System.out.println("-------10회이상 누적이면 강제탈퇴입니다.--------");
		}

	}

	@Override
	public void contextDestroyed(Map<String, Object> params) {
		System.out.println("-------로그아웃 하였습니다-------");
	}
	
}
