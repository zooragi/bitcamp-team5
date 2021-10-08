package com.bitcamp.goodplace.Table;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.server.DataProcessor;
import com.bitcamp.server.Request;
import com.bitcamp.server.Response;

public class ThemeTable extends JsonDataTable<Theme> implements DataProcessor{

	public ThemeTable() {
		super("theme.json", Theme.class);
	}

	public void execute(Request request, Response response) {
		switch (request.getCommand()) {
		case "theme.insert":
			themeInsert(request, response);
			break;
		case "theme.list":
			themeList(request, response);
			break;
		case "theme.update":
			themeUpdate(request, response);
			break;
		case "theme.delete":
			themeDelete(request, response);
			break;
		default:
			response.setStatus(Response.FAIL);
			response.setValue("해당 명령을 지원하지 않습니다.");

		}
	}

	private void themeDelete(Request request, Response response) {
		String deleteThemeTitle = request.getObject(String.class);
		Theme deleteTheme = findByTitle(deleteThemeTitle);
		list.remove(deleteTheme);
		response.setStatus(Response.SUCCESS);
		response.setValue(deleteTheme.getTitle());
	}

	private void themeUpdate(Request request, Response response) {
		Theme theme = request.getObject(Theme.class);
		list.set(indexOf(theme), theme);
		response.setStatus(Response.SUCCESS);
	}

	private void themeList(Request request, Response response) {
		response.setValue(list);
		response.setStatus(Response.SUCCESS);
	}

	private void themeInsert(Request request, Response response) {
		Theme theme = request.getObject(Theme.class);
		list.add(theme);
		response.setStatus(Response.SUCCESS);
		response.setValue(list);
	}

	private int indexOf(Theme theme) {
		int i = 0;
		for (Theme t : list) {
			if (t.getNo() == theme.getNo()) {
				return i;
			}
			i++;
		}
		return -1;
	}

//	private User findByThemeNo(int no) {
//		for(User user : list) {
//			for(Theme theme : user.getThemeList()) {
//				if(theme.getNo() == no) {
//					return user;
//				}
//			}
//		}
//		return null;
//	}
//	
	private Theme findByTitle(String title) {
		for (Theme theme : list) {
			if (theme.getTitle().equals(title)) {
				return theme;
			}
		}

		return null;
	}
//	
//	private int indexOfTheme(Theme theme) {
//		for(User user : list) {
//			int i = 0;
//			for(Theme t : user.getThemeList()) {
//				if(t.getNo() == theme.getNo()) {
//					return i;
//				}
//				i++;
//				
//			}
//		}
//		return -1;
//	}
}
