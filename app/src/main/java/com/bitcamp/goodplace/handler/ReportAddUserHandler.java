package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;

import com.bitcamp.goodplace.App;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportAddUserHandler implements Command {

	List<ReportUser> reportUserList;
	List<User> userList;

	public ReportAddUserHandler(List<User> userList, List<ReportUser> reportUserList) {
		this.reportUserList = reportUserList;
		this.userList = userList;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

		System.out.println("[유저 신고]");
		int count;
		if (reportUserList.size() == 0) {
			count = 0;
		} else {
			count = reportUserList.get(reportUserList.size() - 1).getNo();
		}

		ReportUser reportUser = new ReportUser();

		String userNickName = Prompt.inputString("신고할 유저의 닉네임을 입력해주세요");
		if (findByName(userNickName) == null) {
			System.out.println("해당하는 유저가 없습니다");
			return;
		}
		
		if(userNickName.equals(AuthLoginHandler.getLoginUser().getNickName())) {
    	System.out.println("자신을 신고할 수 없습니다.");
    	return;
    }
		
		reportUser.setUserName(findByName(userNickName).getNickName());

		System.out.println();

		String content = Prompt.inputString("신고 사유를 기입해주세요");
		reportUser.setNo(++count);
		reportUser.setContent(content);
		reportUser.setRegisteredDate(new Date(System.currentTimeMillis()));
		reportUser.setWriter(AuthLoginHandler.getLoginUser());
		reportUser.setState(Report.WAITING);
		count = findByName(userNickName).getReportedCount();
		findByName(userNickName).setReportedCount(++count);
		reportUserList.add(reportUser);
		App.reportList.add(reportUser);
		System.out.println("테마 신고가 완료되었습니다.");

	}

	private User findByName(String userNickName) {
		for (User user : userList) {
			if (user.getNickName().equals(userNickName)) {
				return user;
			}
		}
		return null;
	}
	
}
