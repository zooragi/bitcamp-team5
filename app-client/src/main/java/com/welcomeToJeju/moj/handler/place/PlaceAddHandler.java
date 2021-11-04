package com.welcomeToJeju.moj.handler.place;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.google.gson.Gson;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Comment;
import com.welcomeToJeju.moj.domain.Photo;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.KakaoMapApi;
import com.welcomeToJeju.util.KakaoVo;
import com.welcomeToJeju.util.Prompt;

public class PlaceAddHandler implements Command {

  PlaceDao placeDao;
  SqlSession sqlSession;
  public PlaceAddHandler(PlaceDao placeDao, SqlSession sqlSession) {
    this.placeDao = placeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    Gson gson = new Gson();
    KakaoMapApi kakao = new KakaoMapApi();

    System.out.println("[장소 등록하기]");

    Theme theme = (Theme) request.getAttribute("theme");

    if (theme == null) {
      System.out.println("등록된 테마 없음!");
      System.out.println();
      return;
    }

    Place place = new Place();
    KakaoVo selectedPlace = new KakaoVo();

    ArrayList<KakaoVo> filterPlace = new ArrayList<>();
    while (true) {
      Object[] SearchedPlaces = kakao.searchPlace(Prompt.inputString("장소 이름 > "));

      for(int i = 0 ; i < SearchedPlaces.length ; i++) {
        selectedPlace = gson.fromJson(gson.toJson(SearchedPlaces[i]),KakaoVo.class);

        if(selectedPlace.getAddress_name().contains("제주")) {
          filterPlace.add(selectedPlace);
        }
      }

      if(filterPlace.size() == 0) {
        System.out.println("검색된 장소가 없습니다.");
        continue;
      }
      int i = 1;
      for(KakaoVo kakaoVo : filterPlace) {
        System.out.printf("%d. %s, %s, %s, %s\n",i++,kakaoVo.getPlace_name(),
            kakaoVo.getAddress_name(),
            kakaoVo.getX(),
            kakaoVo.getY()
            );
      }

      int num = Prompt.inputInt("번호 > ");
      if ( num == 0 ) continue;
      if(num > filterPlace.size()) {
        System.out.println("잘못된 번호!");
        continue;
      } 

      selectedPlace = gson.fromJson(gson.toJson(filterPlace.get(num-1)),KakaoVo.class);
      place.setId(selectedPlace.getId());
      place.setStoreAddress(selectedPlace.getAddress_name());
      place.setStoreName(selectedPlace.getPlace_name());
      place.setxCoord(selectedPlace.getX());
      place.setyCoord(selectedPlace.getY());

      ArrayList<Photo> photos = new ArrayList<>();
      while(true) {
        Photo photo = new Photo();
        String photoName = Prompt.inputString("사진 (종료 : 엔터) > ");
        if(photoName.length() == 0) break;
        photo.setFilePath(photoName);
        photos.add(photo);
      }

      place.setPhotos(photos);

      break;
    }
    Comment comment = new Comment();
    String comment_content = Prompt.inputString("장소 후기 > ");
    comment.setComment(comment_content);
    place.getComments().add(comment);
    
    Place findPlace = placeDao.findByPlaceId(selectedPlace.getId());
    if(findPlace == null) {
    	placeDao.insert(place);
    }
    
    
    HashMap<String,Object> param1 = new HashMap<>();
    HashMap<String,Object> param2 = new HashMap<>();
    HashMap<String,Object> param3 = new HashMap<>();
    
    param3.put("themeNo", theme.getNo());
    param3.put("placeId", selectedPlace.getId());
    param3.put("userNo", AuthLoginHandler.getLoginUser().getNo());
    placeDao.insertPlaceUserTheme(param3);

    for(Comment cmt : place.getComments()) {
      param1.put("placeId", place.getId());
      param1.put("userNo", AuthLoginHandler.getLoginUser().getNo());
      param1.put("comment", cmt.getComment());
      placeDao.insertComment(param1);
    }


    for(Photo photo : place.getPhotos()) {
      param2.put("placeId", place.getId());
      param2.put("userNo", AuthLoginHandler.getLoginUser().getNo());
      param2.put("filePath", photo.getFilePath());
      placeDao.insertPhoto(param2);
    }

    sqlSession.commit();
    System.out.println("장소 등록 완료!");
  }
}