package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;

public class ReportMyListHandler implements Command {

	ReportDao reportDao;

	public ReportMyListHandler(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		int index = 1;
		System.out.println("[나의 신고 목록보기]");
		ArrayList<ReportTheme> reportThemeList = (ArrayList<ReportTheme>) reportDao.findThemeAll();
		ArrayList<ReportUser> reportUserList = (ArrayList<ReportUser>) reportDao.findUserAll();
		ArrayList<Report> reportList = new ArrayList<>();
		reportList.addAll(reportUserList);
		reportList.addAll(reportThemeList);
		for (Report report : reportList) {
			if (AuthLoginHandler.getLoginUser().getNickName().equals(report.getWriter().getNickName())) {
				String reportType = report.getClass().getName().contains("Theme") ? "테마" : "유저";
				String reportedName = reportType.equals("테마") ? ((ReportTheme) report).getReportedThemeTitle()
						: ((ReportUser) report).getReportedUserName();
				if(reportedName == null) continue;
				
				System.out.printf("(%s)\n", index++);
				System.out.printf("신고 유형 > <%s> %s\n", reportType, reportedName);
				System.out.println("신고 내용 > " + report.getContent());
				System.out.println("신고 날짜 > " + report.getRegisteredDate());
				System.out.println("신고 상태 > " + report.getState());
			}

		}

	}
}