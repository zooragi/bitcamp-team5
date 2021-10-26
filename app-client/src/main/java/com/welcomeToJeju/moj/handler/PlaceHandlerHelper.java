package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

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
	
	public static Place promptPlace(ArrayList<Place> list) {
		int index = 1;
    for (Place place : list) {
      System.out.printf("<%d>\n", index++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
    }
		int selectNum;
		while(true) {
			selectNum = Prompt.inputInt("번호 선택 > ");
			if(selectNum > list.size() || selectNum < 0) {
				System.out.println("다시 입력 해주세요.");
				continue;
			}
			break;
		}
		
		return list.get(selectNum-1);
		
	}
	
}
