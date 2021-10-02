package com.bitcamp.goodplace;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class ClientApp {

	static RequestAgent requestAgent;

	public static void main(String[] args) throws Exception {
		System.out.println("[PMS 클라이언트]");

		requestAgent = new RequestAgent("127.0.0.1", 8888);

		while (true) {
			String command = Prompt.inputString("명령 > ");

			if (command.equals("/user/add")) {
				addUser();
			} else if (command.equals("/user/detail")) {
//    		detailUser();
			} else {
				requestAgent.request(command, null);

				if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
					String result = requestAgent.getObject(String.class);
					System.out.println("응답 > " + result);
				} else {
					System.out.println("요청 실패");
				}
			}
			if(command.equalsIgnoreCase("quit")) {
				break;
			}
		}

		System.out.println("2) 서버와의 접속을 끊음");
		requestAgent.close();
	}

	private static void addUser() throws Exception {
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
