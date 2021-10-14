package com.bitcamp.goodplace.Table;

import java.util.Map;

import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.server.DataProcessor;
import com.bitcamp.server.Request;
import com.bitcamp.server.Response;

public class UserTable extends JsonDataTable<User> implements DataProcessor {

	public UserTable() {
		super("user.json", User.class);
	}

	public void execute(Request request, Response response) {
		switch (request.getCommand()) {
		case "user.insert":
			insert(request, response);
			break;
		case "user.selectOne":
			selectOne(request, response);
			break;
		case "user.selectList":
			selectList(request, response);
			break;
		case "user.selectOneByEmailPassword":
			selectOneByEmailPassword(request, response);
			break;
		case "user.search":
			search(request, response);
			break;
		case "user.update":
			update(request, response);
			break;
		case "user.likedUser.insert":
			likedUserInsert(request, response);
			break;
		case "user.likedUser.list":
			likedUserList(request, response);
			break;
		case "user.likedUser.delete":
			likedUserDelete(request, response);
			break;
		default:
			response.setStatus(Response.FAIL);
			response.setValue("해당 명령을 지원하지 않습니다.");

		}
	}

  private void likedUserDelete(Request request, Response response) {
    String loginUserName = request.getParameter("loginUser");
    String likedUserName = request.getParameter("likedUser");
    User loginUser = null;
    User likedUser = null;
    for(User u : list) {
      if(u.getNickName().equals(loginUserName)) {
        loginUser = u;
        break;
      }
    }

    for(User u : list) {
      if(u.getNickName().equals(likedUserName)) {
        likedUser = u;
        break;
      }
    }
    loginUser.getLikedUsers().remove(likedUser);
    response.setStatus(Response.SUCCESS);

  }

	private void likedUserList(Request request, Response response) {
		User loginUser = request.getObject(User.class);
    response.setStatus(Response.SUCCESS);
    response.setValue(loginUser.getLikedUsers());

	}

	private void likedUserInsert(Request request, Response response) {
		String loginUserName = request.getParameter("loginUser");
		String likedUserName = request.getParameter("likedUser");
		User loginUser = null;
		User likedUser = null;
		for (User u : list) {
			if (u.getNickName().equals(loginUserName)) {
				loginUser = u;
				break;
			}
		}

		for (User u : list) {
			if (u.getNickName().equals(likedUserName)) {
				likedUser = u;
				break;
			}
		}
		loginUser.getLikedUsers().add(likedUser);
		response.setStatus(Response.SUCCESS);

	}

	private void update(Request request, Response response) {
		User user = request.getObject(User.class);
		list.set(indexOf(user), user);
		response.setStatus(Response.SUCCESS);
	}

	private void search(Request request, Response response) {
		String name = request.getObject(String.class);
		response.setStatus(Response.SUCCESS);
		response.setValue(findByName(name));
	}

	private void selectList(Request request, Response response) {
		response.setStatus(Response.SUCCESS);
		response.setValue(list);
	}

	private void selectOneByEmailPassword(Request request, Response response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = null;
		for (User u : list) {
			if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
				user = u;
				break;
			}
		}

		if (user != null) {
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
		Map<String, String> params = request.getObject(Map.class);
		User user = findByNo(Integer.parseInt(params.get("no")));
		if (user != null) {
			response.setStatus(Response.SUCCESS);
			response.setValue(user);
		} else {
			response.setStatus(Response.FAIL);
			response.setValue("해당 번호의 게시글이 없습니다.");
		}
	}

	private User findByNo(int no) {
		for (User user : list) {
			if (user.getNo() == no) {
				return user;
			}
		}
		return null;
	}

	private User findByName(String name) {
		for (User user : list) {
			if (user.getNickName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	private int indexOf(User user) {
		int i = 0;
		for (User u : list) {
			if (u.getNo() == user.getNo()) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
