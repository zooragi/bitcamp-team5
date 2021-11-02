package com.welcomeToJeju.moj.listener;

import java.util.Map;
import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.moj.domain.User;

public class LoginListener implements UserContextListener{

  @Override
  public void contextLogin(Map<String, Object> params) {
    User currentUser = (User) params.get("currentUser");
    System.out.println("**************************************");
    System.out.println("\t    제주옵서예🍊!");
    System.out.printf("\t* %s님 환영합니다*\t\n",currentUser.getNickname());
    System.out.println("**************************************");
    if(currentUser.getWarningCount() > 2) {
      System.out.printf("-----------경고 %d회 누적입니다.------------\n",currentUser.getWarningCount());
      System.out.println("------10회이상 누적이면 강제탈퇴입니다.-------");
    }

  }

  @Override
  public void contextLogout(Map<String, Object> params) {
    System.out.println("-------로그아웃 하였습니다-------");
  }

}