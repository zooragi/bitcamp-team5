package com.welcomeToJeju.moj.Table;

import java.util.ArrayList;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.server.DataProcessor;
import com.welcomeToJeju.server.Request;
import com.welcomeToJeju.server.Response;

public class ThemeTable extends JsonDataTable<Theme> implements DataProcessor{

  public ThemeTable() {
    super("theme.json", Theme.class);
  }

  @Override
  public void execute(Request request, Response response) {
    switch (request.getCommand()) {
      case "theme.insert":
        insert(request, response);
        break;
      case "theme.list":
        selectList(request, response);
        break;
      case "theme.search":
        search(request, response);
        break;
      case "theme.hashtag.search":
        hashtagSearch(request, response);
        break;
      case "theme.selectedOne":
        selectedOne(request, response);
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
      case "theme.place.delete":
        placeDelete(request,response);
        break;
      case "theme.liked.insert":
        likedInsert(request, response);
        break;
      case "theme.liked.delete":
        likedDelete(request, response);
        break;
      case "theme.liked.list":
        likedList(request, response);
        break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");

    }
  }

  private void likedList(Request request, Response response) {
    int userNo = Integer.parseInt(request.getObject(String.class));
    ArrayList<Theme> likedThemeList = findLikedThemeByUserNo(userNo);
    response.setValue(likedThemeList);
    response.setStatus(Response.SUCCESS);
  }

  private void likedDelete(Request request, Response response) {
    int themeNo =Integer.parseInt(request.getParameter("themeNo")) ;
    int userNo = Integer.parseInt(request.getParameter("userNo"));
    Theme theme = findByNo(themeNo);
    theme.getLikedThemeUserNo().remove(Integer.valueOf(userNo));
    response.setStatus(Response.SUCCESS);
  }

  private void likedInsert(Request request, Response response) {
    int themeNo =Integer.parseInt(request.getParameter("themeNo")) ;
    int userNo = Integer.parseInt(request.getParameter("userNo"));
    Theme theme = findByNo(themeNo);
    theme.getLikedThemeUserNo().add(userNo);
    response.setStatus(Response.SUCCESS);
  }

  private void hashtagSearch(Request request, Response response) {
    String hashtag = request.getObject(String.class);
    ArrayList<Theme> hashtagSearchedList = findByHashtag(hashtag);
    response.setStatus(Response.SUCCESS);
    response.setValue(hashtagSearchedList);

  }

  private void search(Request request, Response response) {
    String title = request.getObject(String.class);
    Theme theme = findByTitle(title);
    response.setStatus(Response.SUCCESS);
    response.setValue(theme);
  }

  private void selectedOne(Request request, Response response) {
    Theme theme = findByTitle(request.getObject(Theme.class).getTitle());
    response.setStatus(Response.SUCCESS);
    response.setValue(theme);
  }

  private void placeDelete(Request request, Response response) {
    Place place = findByPlace(request.getObject(Place.class));
    Theme theme = findByTitle(place.getTheme().getTitle());
    theme.getPlaceList().remove(place);
    response.setStatus(Response.SUCCESS);
    response.setValue(place.getStoreName());
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
  private Theme findByNo(int no) {
    for (Theme theme : list) {
      if (theme.getNo() == no) {
        return theme;
      }
    }
    return null;
  }

  private Place findByPlace(Place place) {
    for(Theme theme : list) {
      if(place.getTheme().getTitle().equals(theme.getTitle())){
        for(Place p : theme.getPlaceList()) {
          if(p.getStoreName().equals(place.getStoreName())) return p;
        }
      }
    }
    return null;
  }

  private ArrayList<Theme> findByHashtag(String hashtag){
    ArrayList<Theme> searchedThemeList = new ArrayList<>();
    for(Theme t : list) {
      for(String h : t.getHashtags()) {
        if(h.equals(hashtag)) {
          searchedThemeList.add(t);
        }
      }
    }
    return searchedThemeList;
  }

  private ArrayList<Theme> findLikedThemeByUserNo(int userNo){
    ArrayList<Theme> likedUserList = new ArrayList<>();
    for(Theme t : list) {
      for(int no : t.getLikedThemeUserNo()) {
        if(userNo == no) {
          likedUserList.add(t);
          break;
        }
      }
    }
    return likedUserList;
  }

}
