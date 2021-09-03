package com.bitcamp.goodplace.Handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class FullThemeSearchAreaHandler implements Command{

	public void execute() {
		System.out.println("[지역 검색]");

		String input = Prompt.inputString("지역을 입력하세요> ");

	}

}
