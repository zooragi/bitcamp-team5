package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportUserProcessingHandler implements Command {

	List<ReportUser> reportUserList;
	List<User> userList;

	public ReportUserProcessingHandler(List<User> userList, List<ReportUser> reportUserList) {
		this.reportUserList = reportUserList;
		this.userList = userList;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

		List<User> reportedUsers = new ArrayList<>();
		int index = 1;

		System.out.println("[유저] 신고 처리]");
		if (reportUserList.size() == 0) {
			System.out.println("테마 신고 목록이 없습니다.");
			return;
		}

		setReportedTheme(reportedUsers);
		Collections.sort(reportedUsers, (a, b) -> b.getReportedCount() - a.getReportedCount());

		for (User user : reportedUsers) {
			System.out.printf("%d. [%d회] %s \n", index++, user.getReportedCount(), user.getNickName());
		}

		while (true) {
			String selected = Prompt.inputString("처리할 유저(빈 문자열 : 취소) : ");
			if (selected.length() == 0)
				return;

			int selectedNum = Integer.parseInt(selected);
			if (selectedNum > reportedUsers.size() || selectedNum < 0) {
				System.out.println("잘못된 번호이다");
				continue;
			}

			showReportedThemeInfo(reportedUsers, selectedNum);

			break;
		}
		while (true) {
			String isWaring = Prompt.inputString("경고를 주시겠습니까?(y/N) : ");
			if (isWaring.equalsIgnoreCase("y")) {
				return;
			} else if (isWaring.equalsIgnoreCase("n")) {
				return;
			} else {
				System.out.println("잘못된 문자열 입니다.");
				continue;
			}

		}
	}

	private void setReportedTheme(List<User> reportedUsers) {
		for (User user : userList) {
			if (user.getReportedCount() != 0) {
				reportedUsers.add(user);
			}
		}
	}

	private void showReportedThemeInfo(List<User> reportedUsers, int selectedNum) {
		int index = 1;
		String selectedReportUserTitle = reportedUsers.get(selectedNum - 1).getNickName();
		for (ReportUser report : reportUserList) {
			if (selectedReportUserTitle.equals(report.getUserName())) {
				System.out.printf("(%s)\n", index++);
				String reportedName = ((ReportUser) report).getUserName();
				System.out.println("신고 당한 유저 : " + reportedName);
				System.out.println("신고 한 유저 : " + report.getWriter().getNickName());
				System.out.println("신고 내용 : " + report.getContent());
				System.out.println("신고 날짜 : " + report.getRegisteredDate());
				System.out.println("신고 상태 : " + report.getState());
			}
		}
	}
}
