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

    Socket socket = serverSocket.accept();
    System.out.println("클라이언트가 접속했음");
    
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<>();
    dataProcessorMap.put("user.", new UserTable());
    dataProcessorMap.put("theme.", new ThemeTable());

    RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
    requestProcessor.service();
    requestProcessor.close();

    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    for (DataProcessor dataProcessor : dataProcessors) {
      if (dataProcessor instanceof JsonDataTable) {
        ((JsonDataTable<?>)dataProcessor).save();
      }
    }
    
    System.out.println("서버 종료");
    serverSocket.close();
	}
}
