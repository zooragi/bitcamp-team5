package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.goodplace.domain.User;

public class ReportListHandler implements Command{
  
	List<Report> reportList;
  List<User> userList;
	
	public ReportListHandler(List<User> userList, List<Report> reportList) {
    this.reportList = reportList;
    this.userList = userList;
  }
  
  @Override
  public void execute(CommandRequest request) throws Exception {
  	
  	System.out.println("[신고 목록]");
  	
  	if(AuthLoginHandler.getUseAccessLevel() == 6) {
  		for(Report report : reportList) {
  			showReportDetailInfo(report);
  		}
    	return;
  	}
  	
  	for(Report report : reportList) {
  		if(AuthLoginHandler.getLoginUser().equals(report.getWriter())) {
  			showReportDetailInfo(report);
  		}

  	}
  	
  }
  private void showReportDetailInfo(Report report) {
  	int index = 1;
		System.out.printf("(%s)\n",index++);
		String reportType = report.getClass().getName().contains("Theme") ? "테마" : "유저";
		String reportedName = reportType.equals("테마") ? ((ReportTheme)report).getTheme().getTitle() : ((ReportUser)report).getUser().getNickName();
		System.out.printf("신고 유형 : %s, %s\n" ,reportType, reportedName);
		System.out.println("신고 내용 : " + report.getContent());
		System.out.println("신고 날짜 : " + report.getRegisteredDate());
		System.out.println("신고 상태 : " + report.getState());
  }

}
