package com.welcomeToJeju.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import com.google.gson.Gson;
import com.welcomeToJeju.moj.Table.JsonDataTable;
import com.welcomeToJeju.moj.Table.ReportTable;
import com.welcomeToJeju.moj.Table.ThemeTable;
import com.welcomeToJeju.moj.Table.UserTable;

public class RequestProcessor extends Thread {
  Socket socket;
  HashMap<String, DataProcessor> dataProcessorMap;

  UserTable userTable = new UserTable();
  ThemeTable themeTable = new ThemeTable();
  ReportTable reportTable = new ReportTable();

  public RequestProcessor(Socket socket, HashMap<String, DataProcessor> dataProcessorMap) throws Exception {
    this.socket = socket;
    this.dataProcessorMap = dataProcessorMap;
  }

  @Override
  public void run() {
    try (Socket socket = this.socket;
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

      Set<String> dataProcessorNames = dataProcessorMap.keySet();

      String command = in.readLine();
      Request request = new Request(command, in.readLine());
      Response response = new Response();

      if (command.equals("quit")) {
        response.setStatus("success");
        response.setValue("goodbye");
        sendResult(response,out);
        return;
      }

      DataProcessor dataProcessor = null;

      for (String key : dataProcessorNames) {
        if (command.startsWith(key)) {
          dataProcessor = dataProcessorMap.get(key);
          break;
        }
      }

      if (dataProcessor != null) {
        dataProcessor.execute(request, response);
      } else {
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령어를 처리할 수 없습니다.");
      }
      sendResult(response,out);
      saveData();

    } catch (Exception e) {
      System.out.println("클라이언트 요청 처리 중 오류 발생!");

    }

  }
  private void saveData() throws Exception {
    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    for (DataProcessor dataProcessor : dataProcessors) {
      if (dataProcessor instanceof JsonDataTable) {
        ((JsonDataTable<?>)dataProcessor).save();
      }
    }
  }
  private void sendResult(Response response, PrintWriter out) throws Exception {
    out.println(response.status);
    if (response.getValue() != null) {
      out.println(new Gson().toJson(response.getValue()));
    } else {
      out.println();
    }
    out.flush();
  }
}
