package com.welcomeToJeju.moj.Table;

import java.util.ArrayList;
import java.util.Map;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.server.DataProcessor;
import com.welcomeToJeju.server.Request;
import com.welcomeToJeju.server.Response;

public class UserTable extends JsonDataTable<User> implements DataProcessor {

  public UserTable() {
    super("user.json", User.class);
  }

  @Override
  public void execute(Request request, Response response) {
    switch (request.getCommand()) {
      case "user.insert":
        insert(request, response);
        break;
      case "user.delete":
        delete(request, response);
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

  private void delete(Request request, Response response) {
    int no = Integer.parseInt(request.getObject(String.class));
    User user = findByNo(no);
    list.remove(user);
    response.setStatus(Response.SUCCESS);
  }

  private void likedUserDelete(Request request, Response response) {
    int likedUserNo= Integer.parseInt(request.getParameter("likedUserNo"));
    int loginUserNo = Integer.parseInt(request.getParameter("loginUserNo"));
    User likedUser = findByNo(likedUserNo);
    likedUser.getLikedUserNo().remove(Integer.valueOf(loginUserNo));
    response.setStatus(Response.SUCCESS);
  }

  private void likedUserList(Request request, Response response) {
    int userNo = Integer.parseInt(request.getObject(String.class));
    ArrayList<String> likedUserList = findLikedUserByUserName(userNo); 
    response.setStatus(Response.SUCCESS);
    response.setValue(likedUserList);
  }

  private void likedUserInsert(Request request, Response response) {
    int likedUserNo= Integer.parseInt(request.getParameter("likedUserNo"));
    int loginUserNo = Integer.parseInt(request.getParameter("loginUserNo"));
    User likedUser = findByNo(likedUserNo);
    likedUser.getLikedUserNo().add(loginUserNo);
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

  private ArrayList<String> findLikedUserByUserName(int userNo){
    ArrayList<String> likedUser = new ArrayList<>();
    for(User u : list) {
      for(int no : u.getLikedUserNo()) {
        if(no == userNo) {
          likedUser.add(u.getNickName());
        }
      }
    }
    return likedUser;
  }

}
