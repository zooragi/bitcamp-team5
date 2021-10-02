package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import com.bitcamp.goodplace.domain.Place;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.KakaoMapApi;
import com.bitcamp.util.KakaoVo;
import com.bitcamp.util.Prompt;
import com.google.gson.Gson;

public class PlaceAddHandler extends AbstractPlaceHandler {

  @Override
  public void execute(CommandRequest request) {
    Gson gson = new Gson();
    KakaoMapApi kakao = new KakaoMapApi();

    System.out.println("[장소 등록하기]");

    Theme theme = (Theme) request.getAttribute("searchedTheme");

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

        if(selectedPlace.getAddress_name().contains("서울")) {
          filterPlace.add(selectedPlace);
        }
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
      place.setStoreAddress(selectedPlace.getAddress_name());
      place.setStoreName(selectedPlace.getPlace_name());
      place.setxCoord(selectedPlace.getX());
      place.setyCoord(selectedPlace.getY());
      place.setTheme(theme/*.getTitle()*/);

      break;
    }

    place.getComments().add(Prompt.inputString("장소 후기 > "));

    theme.getPlaceList().add(place);
    System.out.println("장소 등록 완료!");
  }
}