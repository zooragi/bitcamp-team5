package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class ReportUserProcessingHandler implements Command {

	ReportDao reportDao;
	ThemePrompt themePrompt;
	UserPrompt userPrompt;
	
  public ReportUserProcessingHandler(ReportDao reportDao, ThemePrompt themePrompt,UserPrompt userPrompt) {
  	this.reportDao  = reportDao;
  	this.themePrompt = themePrompt;
  	this.userPrompt = userPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    List<User> countedUserList = new ArrayList<>();
    List<ReportUser> reportUserList = reportDao.findUserAll();
    User selectedUser;
    int index = 1;

    System.out.println("[유저 신고 처리하기]");
    if (reportUserList.size() == 0) {
      System.out.println("유저 신고 없음!");
      return;
    }

    countedUserList = userPrompt.setCountedUser();
    Collections.sort(countedUserList, (a, b) -> b.getReportedCount() - a.getReportedCount());

    for (User user : countedUserList) {
      System.out.printf("%d. [%d회] %s \n", index++, user.getReportedCount(), user.getNickName());
    }

    while (true) {
      int selectedNum = Prompt.inputInt("처리할 유저(취소 : 0번) > ");
      if (selectedNum == 0) return;

      if (selectedNum > countedUserList.size() || selectedNum < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }
      selectedUser = countedUserList.get(selectedNum-1);

      showReportedThemeInfo(reportUserList,countedUserList, selectedNum);

      break;
    }
    
    while (true) {
      String isWaring = Prompt.inputString("경고주기 (y/N) > ");
      
      
      if (isWaring.equalsIgnoreCase("y")) {
        userPrompt.increaseWariningCount(selectedUser);
        break;
      } else if (isWaring.equalsIgnoreCase("n")) {
        return;
      } else {
        System.out.println("잘못된 문자!");
        continue;
      }
    }
    
  }

  private void showReportedThemeInfo(List<ReportUser> reportUserList, List<User> countedUserList, int selectedNum) {
    int index = 1;
    String selectedReportUserTitle = countedUserList.get(selectedNum - 1).getNickName();
    for (ReportUser report : reportUserList) {
      if (selectedReportUserTitle.equals(report.getReportedUserName())) {
        System.out.printf("(%s)\n", index++);
        String reportedName = report.getReportedUserName();
        System.out.println("신고 당한 유저 : " + reportedName);
        System.out.println("신고 한 유저 : " + report.getWriter().getNickName());
        System.out.println("신고 내용 : " + report.getContent());
        System.out.println("신고 날짜 : " + report.getRegisteredDate());
        System.out.println("신고 상태 : " + report.getState());
      }
    }
  }
}