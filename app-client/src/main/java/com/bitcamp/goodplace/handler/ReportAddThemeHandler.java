package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.ArrayList;

import com.bitcamp.goodplace.dao.ReportDao;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.util.Prompt;

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
    ArrayList<Report> reportList = (ArrayList<Report>) reportDao.findAll();
    
		loop: while (true) {
			uniqueNum = Prompt.inputInt("고유 번호(취소 : 엔터) > ");
			for (Report r : reportList) {
				if (r.getNo() == uniqueNum) {
					System.out.println("존재하는 번호입니다. 다시 입력 하시오.");
					continue loop;
				}
			}
			break;
		}

    ReportTheme reportTheme = new ReportTheme();
    
    String themeTitle = Prompt.inputString("신고할 테마 이름 > ");
    Theme reportedTheme = themePrompt.findByTitle(themeTitle);
    
    if(reportedTheme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    if(reportedTheme.getThemeOwnerName()
        .equals(AuthLoginHandler.getLoginUser().getNickName())) {
      System.out.println("본인의 테마 신고 불가!");
      return;
    }

    reportTheme.setReportedThemeTitle(reportedTheme.getTitle());

    System.out.println();

    String content = Prompt.inputString("신고 사유 > ");
    reportTheme.setNo(uniqueNum);
    reportTheme.setContent(content);
    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportTheme.setWriter(AuthLoginHandler.getLoginUser());
    reportTheme.setState(Report.WAITING);
    
    reportDao.themeInsert(reportTheme);
    
    System.out.println("테마 신고 완료!");

    
  }

}