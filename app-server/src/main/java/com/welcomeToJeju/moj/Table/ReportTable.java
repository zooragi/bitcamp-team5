package com.welcomeToJeju.moj.Table;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.server.DataProcessor;
import com.welcomeToJeju.server.Request;
import com.welcomeToJeju.server.Response;

public class ReportTable extends JsonDataTable<Report> implements DataProcessor{

  public ReportTable() {
    super("report.json", Report.class);
  }

  @Override
  public void execute(Request request, Response response) {
    switch(request.getCommand()) {
      case "report.themeInsert": themeInsert(request,response); break;
      case "report.userInsert": userInsert(request,response); break;
      case "report.selectList": selectList(request,response); break;
      case "report.selectThemeList": selectThemeList(request,response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void selectThemeList(Request request, Response response) {
    String jsonStr = new Gson().toJson(list);
    ArrayList<Report> reportThemeList = (ArrayList<Report>) Request.getReportInheritedChildObjects(jsonStr,"reportedThemeTitle");
    response.setValue(reportThemeList);
    response.setStatus(Response.SUCCESS);
  }

  private void themeInsert(Request request, Response response) {
    ReportTheme report = request.getObject(ReportTheme.class);
    list.add(report);
    response.setStatus(Response.SUCCESS);
  }
  private void userInsert(Request request, Response response) {
    ReportUser report = request.getObject(ReportUser.class);
    list.add(report);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) {
    response.setValue(list);
    response.setStatus(Response.SUCCESS);
  }	
}
