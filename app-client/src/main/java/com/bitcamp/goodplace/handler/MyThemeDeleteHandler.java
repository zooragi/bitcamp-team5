package com.bitcamp.goodplace.handler;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.request.RequestAgent;
import com.bitcamp.util.Prompt;

public class MyThemeDeleteHandler implements Command {

	RequestAgent requestAgent;

	public MyThemeDeleteHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	public void execute(CommandRequest request) throws Exception{
		System.out.println("[테마 삭제하기]");

		String input = Prompt.inputString("삭제하기(y/N) > ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("테마 삭제 취소!");
			return;
		}
		
		requestAgent.request("user.theme.delete", request.getAttribute("themeTitle"));
		
		
		System.out.printf("%s테마 삭제 완료!",requestAgent.getObject(Theme.class).getTitle());
		System.out.println();
	}

}
