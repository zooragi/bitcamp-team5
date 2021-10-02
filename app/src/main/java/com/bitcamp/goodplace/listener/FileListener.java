package com.bitcamp.goodplace.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.bitcamp.context.ApplicationContextListener;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.goodplace.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileListener implements ApplicationContextListener{

  @SuppressWarnings("unchecked")
  @Override
  public void contextInitialized(Map<String, Object> params) {

    List<User> userList = (List<User>) params.get("userList");
    List<ReportTheme> reportThemeList = (List<ReportTheme>) params.get("reportThemeList");
    List<ReportUser> reportUserList = (List<ReportUser>) params.get("reportUserList");

    loadObject("user.json",userList,User.class);
    loadObject("reportTheme.json",reportThemeList,ReportTheme.class);
    loadObject("reportUser.json",reportUserList,ReportUser.class);      
  }

  @SuppressWarnings("unchecked")
  @Override
  public void contextDestroyed(Map<String, Object> params) {
    List<User> userList = (List<User>) params.get("userList");
    List<ReportTheme> reportThemeList = (List<ReportTheme>) params.get("reportThemeList");
    List<ReportUser> reportUserList = (List<ReportUser>) params.get("reportUserList");      

    saveObjects("user.json",userList);
    saveObjects("reportTheme.json",reportThemeList);
    saveObjects("reportUser.json",reportUserList);
  }

  public <E> void loadObject(String fileName, List<E> list, Class<E> domainType) {
    try (BufferedReader in = new BufferedReader(new FileReader(fileName, Charset.forName("UTF-8")))) {

      StringBuilder strBuilder = new StringBuilder();
      String str;
      while ((str = in.readLine()) != null) {
        strBuilder.append(str);
      }
      Type type = TypeToken.getParameterized(Collection.class, domainType).getType();
      Collection<E> collection = new Gson().fromJson(strBuilder.toString(), type);

      list.addAll(collection);

      System.out.printf("%s 데이터 로딩 완료!\n", fileName);

    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", fileName);
    }
  }

  private void saveObjects(String filepath, List<?> list) {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(new FileWriter(filepath, Charset.forName("UTF-8"))))) {
      out.print(new Gson().toJson(list));

      System.out.printf("%s 데이터 출력 완료!\n", filepath);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n", filepath);
      e.printStackTrace();
    }
  }

}