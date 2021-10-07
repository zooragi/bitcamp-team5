package com.bitcamp.goodplace.handler;

import java.util.ArrayList;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.request.RequestAgent;

public class UserPrompt {

	RequestAgent requestAgent;

	public UserPrompt(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	public Theme findByTitle(String themeTitle) throws Exception {
		requestAgent.request("theme.list", null);
		ArrayList<Theme> themeList = new ArrayList<>(requestAgent.getObjects(Theme.class));
		for (Theme theme : themeList)
			if (theme.getTitle().equals(themeTitle)) {
				return theme;
			}

		return null;
	}

}
