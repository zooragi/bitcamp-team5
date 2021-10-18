package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class ReportThemeProcessingHandler implements Command {

	ReportDao reportDao;
	ThemePrompt themePrompt;
	UserPrompt userPrompt;
	
  public ReportThemeProcessingHandler(ReportDao reportDao, ThemePrompt themePrompt,UserPrompt userPrompt) {
  	this.reportDao = reportDao;
  	this.themePrompt = themePrompt;
  	this.userPrompt = userPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    List<Theme> countedThemeList = new ArrayList<>();
    List<ReportTheme> reportThemeList = reportDao.findThemeAll();
    User selectedUser;
    int index = 1;

    System.out.println("[테마 신고 처리하기]");
    if(reportThemeList.size()==0) {
      System.out.println("테마 신고 없음!");
      return;
    }

    countedThemeList = themePrompt.setCountedThemes();
    Collections.sort(countedThemeList, (a,b) -> b.getReportedCount() - a.getReportedCount());

    for(Theme theme : countedThemeList) {
      System.out.printf("%d. [%d회] %s \n", index++,theme.getReportedCount(), theme.getTitle());
    }


    while(true) {
      int selectedNum = Prompt.inputInt("처리할 테마(취소 : 0번) > ");
      if(selectedNum == 0) return;
      if(selectedNum > countedThemeList.size() || selectedNum < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }

      selectedUser = userPrompt.findByNo(countedThemeList.get(selectedNum-1).getThemeOwnerNo()); 

      showReportedThemeInfo(reportThemeList,countedThemeList,selectedNum);

      break;
    }
    
    while(true) {
      String isWaring = Prompt.inputString("경고주기 (y/N) > ");

      if(isWaring.equalsIgnoreCase("y")) {
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

  private void showReportedThemeInfo(List<ReportTheme> reportThemeList, List<Theme> countedThemeList, int selectedNum) {
    int index = 1;
    String selectedReportThemeTitle = countedThemeList.get(selectedNum-1).getTitle();
    for (ReportTheme report : reportThemeList) {
      if(selectedReportThemeTitle.equals(report.getReportedThemeTitle())) {
        System.out.printf("<%s>\n", index++);
        String reportedTitle =report.getReportedThemeTitle();
        System.out.println("신고 당한 테마 > " + reportedTitle);
        System.out.println("신고 한 유저 > " + report.getWriter().getNickName());
        System.out.println("신고 내용 > " + report.getContent());
        System.out.println("신고 날짜 > " + report.getRegisteredDate());
        System.out.println("신고 상태 > " + report.getState());
      }
    }

  }
}