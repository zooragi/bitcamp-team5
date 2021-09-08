package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class UserAddHandler extends AbstractUserHandler{
	public UserAddHandler(List<User> userList) {
		super(userList);
	    User rootUser = new User();
	    rootUser.setNo(1);
	    rootUser.setName("root");
	    rootUser.setId("root");
	    rootUser.setEmail("root");
	    rootUser.setPassword("0000");
	    rootUser.setTel("010-0000-0000");
	    rootUser.setNickName("서울바보");
	    rootUser.setBookMarks(new ArrayList<>());
	    rootUser.setThemeList(new ArrayList<>());

	    userList.add(rootUser);
	    
	    User testUser = new User();
	    testUser.setNo(2);
	    testUser.setName("111");
	    testUser.setId("111");
	    testUser.setEmail("111");
	    testUser.setPassword("111");
	    testUser.setTel("010-0000-0000");
	    testUser.setNickName("서울천재");
	    testUser.setBookMarks(new ArrayList<>());
	    testUser.setThemeList(new ArrayList<>());

	    userList.add(testUser);

	    User testUser2 = new User();
	    testUser2.setNo(3);
	    testUser2.setName("222");
	    testUser2.setId("222");
	    testUser2.setEmail("222.com");
	    testUser2.setPassword("1111");
	    testUser2.setTel("010-0000-1111");
	    testUser2.setNickName("서울촌놈");
	    testUser2.setBookMarks(new ArrayList<>());
	    testUser2.setThemeList(new ArrayList<>());

	    userList.add(testUser2);
	    

		
	}

	public void execute() {
		User user = new User();
		int userNo = 0;

		user.setId(Prompt.inputString("아이디? "));
		user.setPassword(Prompt.inputString("암호? "));
		user.setName(Prompt.inputString("이름? "));
		user.setEmail(Prompt.inputString("이메일? "));
		user.setNickName(Prompt.inputString("닉네임? "));
		user.setTel(Prompt.inputString("전화? "));
	    user.setRegisteredDate(new Date(System.currentTimeMillis()));
	    userNo = userList.get(userList.size()-1).getNo();
		user.setNo(userNo++);

		userList.add(user);
	}

}
