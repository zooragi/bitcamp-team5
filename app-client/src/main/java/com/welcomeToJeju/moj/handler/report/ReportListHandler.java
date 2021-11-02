package com.welcomeToJeju.moj.handler.report;

import java.util.ArrayList;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;

public class ReportListHandler implements Command {

  ReportDao reportDao;

  public ReportListHandler(ReportDao reportDao) {
    this.reportDao = reportDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    ArrayList<ReportUser> reportUserList = (ArrayList<ReportUser>) reportDao.reportUserFindByUserNo(AuthLoginHandler.getLoginUser().getNo());
    ArrayList<ReportTheme> reportThemeList = (ArrayList<ReportTheme>) reportDao.reportThemeFindByUserNo(AuthLoginHandler.getLoginUser().getNo());
    ArrayList<Report> reportList = new ArrayList<>();
    reportList.addAll(reportUserList);
    reportList.addAll(reportThemeList);
    int index = 1;
    for (Report report : reportList) {
      if (AuthLoginHandler.getLoginUser().getNickname().equals(report.getWriter().getNickname())) {
        String reportType = report.getClass().getName().contains("Theme") ? "테마" : "유저";
        String reportedName = reportType.equals("테마") ? ((ReportTheme) report).getReportedTheme().getTitle()
            : ((ReportUser) report).getReportedUser().getNickname();
        if(reportedName == null) continue;

        System.out.printf("(%s)\n", index++);
        System.out.printf("신고 유형 > <%s> %s\n", reportType, reportedName);
        System.out.println("신고 내용 > " + report.getContent());
        System.out.println("신고 날짜 > " + report.getRegisteredDate());
        System.out.println("신고 상태 > " + report.getStatus().getTitle());
      }
    }
  }
}
