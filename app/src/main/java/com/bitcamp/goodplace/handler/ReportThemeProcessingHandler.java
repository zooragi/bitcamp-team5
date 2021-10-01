package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportThemeProcessingHandler implements Command {

	List<ReportTheme> reportThemeList;
	List<User> userList;

	public ReportThemeProcessingHandler(List<User> userList, List<ReportTheme> reportThemeList) {
		this.reportThemeList = reportThemeList;
		this.userList = userList;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

		List<Theme> reportedThemes = new ArrayList<>();
		int index = 1;
		
		System.out.println("[테마 신고 처리]");
		if(reportThemeList.size()==0) {
			System.out.println("테마 신고 목록이 없습니다.");
			return;
		}
		
		setReportedTheme(reportedThemes);
		Collections.sort(reportedThemes, (a,b) -> b.getReportedCount() - a.getReportedCount());
		
		for(Theme theme : reportedThemes) {
			System.out.printf("%d. [%d회] %s \n", index++,theme.getReportedCount(), theme.getTitle());
		}
		
		
		while(true) {
			String selected = Prompt.inputString("처리할 테마(빈 문자열 : 취소) : ");
			if(selected.length() == 0) return;
			
			int selectedNum = Integer.parseInt(selected);
			if(selectedNum > reportedThemes.size() || selectedNum < 0) {
				System.out.println("잘못된 번호이다");
				continue;
			}
			showReportedThemeInfo(reportedThemes,selectedNum);

			break;
		}
		while(true) {
			String isWaring = Prompt.inputString("경고를 주시겠습니까?(y/N) : ");
			if(isWaring.equalsIgnoreCase("y")) {
				return;
			} else if (isWaring.equalsIgnoreCase("n")) {
				return;
			} else {
				System.out.println("잘못된 문자열 입니다.");
				continue;
			}
			
		}
	}
	
	private void setReportedTheme(List<Theme> reportedThemes) {
		for(User user : userList) {
			for(Theme theme : user.getThemeList()) {
				if(theme.getReportedCount() != 0) {
					reportedThemes.add(theme);
				}
			}
		}
	}
	
	private void showReportedThemeInfo(List<Theme> reportedThemes, int selectedNum) {
		int index = 1;
		String selectedReportThemeTitle = reportedThemes.get(selectedNum-1).getTitle();
		for (ReportTheme report : reportThemeList) {
			if(selectedReportThemeTitle.equals(report.getThemeTitle())) {
				System.out.printf("(%s)\n", index++);
//				String reportType = report.getClass().getName().contains("Theme") ? "테마" : "유저";
//				if(reportType.equals("유저"));
				String reportedTitle =((ReportTheme) report).getThemeTitle();
				System.out.println("신고 당한 테마 : " + reportedTitle);
				System.out.println("신고 한 유저 : " + report.getWriter().getNickName());
				System.out.println("신고 내용 : " + report.getContent());
				System.out.println("신고 날짜 : " + report.getRegisteredDate());
				System.out.println("신고 상태 : " + report.getState());
			}
		}
	}
	
}
