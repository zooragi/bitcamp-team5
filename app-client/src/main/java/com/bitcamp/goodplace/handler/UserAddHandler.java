package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class UserAddHandler implements Command{
	RequestAgent requestAgent;
	List<User> userList; 
	
  public UserAddHandler(RequestAgent requestAgent) {
  	this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) throws Exception{

    System.out.println("[회원 가입하기]");

    User user = new User();
//    int userNo = 0;

    user.setEmail(Prompt.inputString("이메일 > "));
    user.setNickName(Prompt.inputString("닉네임 > "));
    user.setPassword(Prompt.inputString("암호 > "));
    user.setRegisteredDate(new Date(System.currentTimeMillis()));
//    userNo = userList.get(userList.size()-1).getNo();
//    user.setNo(++userNo);

    requestAgent.request("user.insert", user);
    
    if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
    	System.out.println("회원 추가 완료");
    } else {
    	System.out.println("회원 삭제 완료");
    }
  }

}
