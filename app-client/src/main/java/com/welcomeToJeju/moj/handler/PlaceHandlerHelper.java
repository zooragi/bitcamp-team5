package com.welcomeToJeju.moj.handler;

import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class PlaceHandlerHelper {

	public static void printPlaceInfo(Theme theme) {
		int index = 1;
    for (Place place : theme.getPlaceList()) {
      System.out.printf("<%d>\n", index++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
      System.out.printf("장소 주소 > %s\n", place.getStoreAddress());
      System.out.printf("위도 > %s\n", place.getxCoord());
      System.out.printf("경도 > %s\n", place.getyCoord());
      System.out.printf("장소 후기> %s\n", place.getComments().toString());
    }
	}
	
	public static void printPlaceName(Theme theme) {
		int index = 1;
    for (Place place : theme.getPlaceList()) {
      System.out.printf("<%d>\n", index++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
    }
	}
	
	public static Place promptPlace(Theme theme) {
		printPlaceName(theme);
		int selectNum;
		while(true) {
			selectNum = Prompt.inputInt("번호 선택 > ");
			if(selectNum > theme.getPlaceList().size() || selectNum < 0) {
				System.out.println("다시 입력 해주세요.");
				continue;
			}
			break;
		}
		
		return theme.getPlaceList().get(selectNum-1);
		
	}
	
}
