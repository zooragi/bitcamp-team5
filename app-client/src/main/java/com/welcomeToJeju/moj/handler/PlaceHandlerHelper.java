package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

import com.welcomeToJeju.moj.domain.Comment;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.util.Prompt;

public class PlaceHandlerHelper {

	public static void printPlaceInfo(ArrayList<Place> list) {
		if(list.size() == 0) {
			System.out.println("등록된 장소들 없음");
			return;
		}
		int index = 1;
    for (Place place : list) {
    	int i = 1;
      System.out.printf("<%d>\n", index++);
      System.out.printf("장소 이름 > %s\n", place.getStoreName());
      System.out.printf("장소 주소 > %s\n", place.getStoreAddress());
      System.out.printf("위도 > %s\n", place.getxCoord());
      System.out.printf("경도 > %s\n", place.getyCoord());
      System.out.printf("[장소 후기] \n");
      for(Comment cm : place.getComments()) {
      	System.out.printf("%d. %s\n", i++, cm.getComment());
      }
      System.out.println();
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
