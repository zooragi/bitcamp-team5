package com.bitcamp.goodplace;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;

import com.bitcamp.goodplace.Table.JsonDataTable;
import com.bitcamp.goodplace.Table.ThemeTable;
import com.bitcamp.goodplace.Table.UserTable;
import com.bitcamp.server.DataProcessor;
import com.bitcamp.server.RequestProcessor;

public class ServerApp {
	public static void main(String[] args) throws Exception{
    System.out.println("[PMS 서버]");

    System.out.println("서버 실행중");
    ServerSocket serverSocket = new ServerSocket(8888);
    
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<>();
    
    dataProcessorMap.put("user.", new UserTable());
    dataProcessorMap.put("theme.", new ThemeTable());

    while(true) {
    	
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트 접속");

      // 1) 새 실행 흐름 생성
      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);

      // 2) 새로 생성한 실행 흐름을 시작시킨다.
      // => run()이 호출될 것이다.
      // => 시작시킨 후 즉시 리턴한다. 
      //    즉 새로 생성한 실행 흐름이 종료될 때까지 기다리지 않는다.
      requestProcessor.start();
    	
    }

    
//    System.out.println("서버 종료");
//    serverSocket.close();
	}
}
