package com.welcomeToJeju.moj.handler;

import java.sql.Date;
import java.util.ArrayList;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportStatus;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class ReportAddThemeHandler implements Command{

	ReportDao reportDao;
	ThemePrompt themePrompt;
	
  public ReportAddThemeHandler(ReportDao reportDao,ThemePrompt themePrompt) {
  	this.reportDao = reportDao;
  	this.themePrompt = themePrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 신고하기]");
    int uniqueNum;

    ReportTheme reportTheme = new ReportTheme();
    
    String themeTitle = Prompt.inputString("신고할 테마 이름 > ");
    Theme reportedTheme = themePrompt.findByTitle(themeTitle);
    
    if(reportedTheme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    if(reportedTheme.getOwner().getNo()
         == (AuthLoginHandler.getLoginUser().getNo())) {
      System.out.println("본인의 테마 신고 불가!");
      return;
    }

    reportTheme.setReportedTheme(reportedTheme);

    System.out.println();
    ReportStatus reportStatus = new ReportStatus();
    reportStatus.setNo(100);
    
    String content = Prompt.inputString("신고 사유 > ");
    reportTheme.setContent(content);
    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportTheme.setWriter(AuthLoginHandler.getLoginUser());
    reportTheme.setState(reportStatus);
    
    reportDao.reportThemeInsert(reportTheme);
    
    int count = reportedTheme.getReportedCount() + 1;
    
    themePrompt.increaseReportedCount(reportedTheme.getNo(),count);
    
    System.out.println("테마 신고 완료!");

    
  }

}