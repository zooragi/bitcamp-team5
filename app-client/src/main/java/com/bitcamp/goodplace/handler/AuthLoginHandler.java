package com.bitcamp.goodplace.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.menu.Menu;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class AuthLoginHandler implements Command{

  List<User> userList;
  static User loginUser;
  static int useAccessLevel = Menu.ACCESS_LOGOUT;
  RequestAgent requestAgent;
//  List<UserContextListener> userListeners = new ArrayList<>();
  User user;

  public static User getLoginUser() {
    return loginUser;
  }
  public static int getUseAccessLevel() {
    return useAccessLevel;
  }

	public AuthLoginHandler(RequestAgent requestAgent/* ,List<UserContextListener> userListeners */) {
    this.requestAgent = requestAgent;
//    this.userListeners = userListeners;
  }

  public void execute(CommandRequest request) throws Exception{
    System.out.println("[로그인]");

    String email = Prompt.inputString("이메일 > ");
    String password = Prompt.inputString("암호 > ");

    if(email.equals("root@test.com") && password.equals("0000")) {
      loginUser = userList.get(0);
      useAccessLevel = Menu.ACCESS_GENERAL | Menu.ACCESS_ADMIN;
      System.out.println("제주정승🍊 환영합니다!");
      return;
    }

    Map<String,String> params = new HashMap<String,String>();
    params.put("email", email);
    params.put("password", password);
    
    requestAgent.request("user.selectOneByEmailPassword", params);

    if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
    	User user = requestAgent.getObject(User.class);
    	loginUser = user;
    	useAccessLevel = Menu.ACCESS_GENERAL;
    } else {
    	System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
    }
    
//      notifyOnLogin();
    
  }


//  private void notifyOnLogin() {
//    HashMap<String,Object> params = new HashMap<>();
//
//    params.put("currentUser", user);
//
//    for (UserContextListener listener : userListeners) {
//      listener.contextLogin(params);
//    }
//  }


}
