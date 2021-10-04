package com.bitcamp.goodplace.Table;

import java.util.Map;

import com.bitcamp.goodplace.domain.User;
import com.bitcamp.server.Request;
import com.bitcamp.server.Response;

public class UserTable extends JsonDataTable<User>{

	public UserTable() {
		super("user.json", User.class);
	}

	public void execute(Request request, Response response) {
		switch (request.getCommand()) {
			case "user.insert": insert(request,response); break;
			case "user.selectOne": selectOne(request,response); break;
			case "user.selectOneByEmailPassword" :selectOneByEmailPassword(request,response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
				
		}
	}

	private void selectOneByEmailPassword(Request request, Response response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = null;
		for(User u : list) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				user = u;
				break;
			}
		}
		
		if(user != null) {
			response.setStatus(Response.SUCCESS);
			response.setValue(user);
		} else {
			response.setStatus(Response.FAIL);
			response.setValue("해당 이메일과 암호를 가진 회원을 찾을 수 없습니다.");
		}
	}

	private void insert(Request request, Response response) {
		User user = request.getObject(User.class);
		list.add(user);
		response.setStatus("success");
		response.setValue(user);
	}
	
	@SuppressWarnings("unchecked")
	private void selectOne(Request request, Response response) {
		Map<String,String> params = request.getObject(Map.class);
		User user = findByNo(Integer.parseInt(params.get("no")));
		if(user != null) {
			response.setStatus(Response.SUCCESS);
			response.setValue(user);	
		} else {
			response.setStatus(Response.FAIL);
			response.setValue("해당 번호의 게시글이 없습니다.");
		}
	}

	private User findByNo(int no) {
		for(User user : list) {
			if(user.getNo() == no) {
				return user;
			}
		}
		return null;
	}
}
