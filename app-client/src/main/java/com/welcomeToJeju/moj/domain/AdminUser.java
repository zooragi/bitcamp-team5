package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User{
	private List<String> category = new ArrayList<>();

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}
	
}
