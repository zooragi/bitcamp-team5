package com.bitcamp.goodplace.Table;

import com.bitcamp.goodplace.domain.Place;
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
			insert(request, response);
			break;
		case "theme.list":
			selectList(request, response);
			break;
		case "theme.update":
			update(request, response);
			break;
		case "theme.delete":
			delete(request, response);
			break;
		case "theme.place.insert":
			placeInsert(request,response);
			break;
		default:
			response.setStatus(Response.FAIL);
			response.setValue("해당 명령을 지원하지 않습니다.");

		}
	}

	private void placeInsert(Request request, Response response) {
		Place place = request.getObject(Place.class);
		Theme theme = findByTitle(place.getTheme().getTitle());
		theme.getPlaceList().add(place);
		response.setStatus(Response.SUCCESS);
	}

	private void delete(Request request, Response response) {
		String deleteThemeTitle = request.getObject(String.class);
		Theme deleteTheme = findByTitle(deleteThemeTitle);
		list.remove(deleteTheme);
		response.setStatus(Response.SUCCESS);
		response.setValue(deleteTheme.getTitle());
	}

	private void update(Request request, Response response) {
		Theme theme = request.getObject(Theme.class);
		list.set(indexOf(theme), theme);
		response.setStatus(Response.SUCCESS);
	}

	private void selectList(Request request, Response response) {
		response.setValue(list);
		response.setStatus(Response.SUCCESS);
	}

	private void insert(Request request, Response response) {
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

	private Theme findByTitle(String title) {
		for (Theme theme : list) {
			if (theme.getTitle().equals(title)) {
				return theme;
			}
		}

		return null;
	}

}
