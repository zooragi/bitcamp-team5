package com.bitcamp.goodplace;

import java.sql.Date;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class ClientApp {
	static RequestAgent requestAgent;
	
	public static void main(String[] args) throws Exception{
		
		requestAgent = new RequestAgent("127.0.0.1",8888);
		
		while(true) {
			String input = Prompt.inputString("명령 > ");
			
			if(input.startsWith("user.")) {
				addUser();
			} else {
				requestAgent.request(input, null);
				
				if(requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
					System.out.println(">>> " + requestAgent.getObject(String.class));
				} else {
					System.out.println("요청 실패");
				}
			}
			
			if(input.equals("quit")) {
				break;
			}
			
		}
		
		requestAgent.close();
		
		Prompt.close();
		
	}

	private static void addUser() throws Exception{
		User user = new User();

		user.setNo(1);
		user.setEmail("aaa");
		user.setPassword("1111");
		user.setNickName("서울천재");
		user.setRegisteredDate(Date.valueOf("2021-10-2"));
		user.setViewCount(0);
		user.setReportedCount(0);
		user.setWarningCount(0);

		requestAgent.request("/user/insert", user);
	}
}
