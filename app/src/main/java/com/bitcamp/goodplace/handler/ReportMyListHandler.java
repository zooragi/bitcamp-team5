package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.ReportUser;

public class ReportMyListHandler implements Command {

  List<Report> reportList;

  public ReportMyListHandler(List<Report> reportList) {
    this.reportList = reportList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    int index = 1;
    System.out.println("[나의 신고 목록보기]");

    for (Report report : reportList) {
      if (AuthLoginHandler.getLoginUser().getNickName().equals(report.getWriter().getNickName())) {

        System.out.printf("(%s)\n", index++);
        String reportType = report.getClass().getName().contains("Theme") ? "테마" : "유저";
        String reportedName = reportType.equals("테마") ? ((ReportTheme) report).getReportedTheme().getTitle()
            : ((ReportUser) report).getReportedUser().getNickName();
        System.out.printf("신고 유형 > <%s> %s\n", reportType, reportedName);
        System.out.println("신고 내용 > " + report.getContent());
        System.out.println("신고 날짜 > " + report.getRegisteredDate());
        System.out.println("신고 상태 > " + report.getState());
      }
    }

  }

}