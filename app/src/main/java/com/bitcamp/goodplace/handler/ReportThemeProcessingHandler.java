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
    User selectedUser;
    int index = 1;

    System.out.println("[테마 신고 처리하기]");
    if(reportThemeList.size()==0) {
      System.out.println("테마 신고 없음!");
      return;
    }

    setReportedTheme(reportedThemes);
    Collections.sort(reportedThemes, (a,b) -> b.getReportedCount() - a.getReportedCount());

    for(Theme theme : reportedThemes) {
      System.out.printf("%d. [%d회] %s \n", index++,theme.getReportedCount(), theme.getTitle());
    }


    while(true) {
      String selected = Prompt.inputString("처리할 테마(취소 : 엔터) > ");
      if(selected.length() == 0) return;

      int selectedNum = Integer.parseInt(selected);
      selectedUser = findUserByUserName(reportedThemes.get(selectedNum-1).getThemeOwnerName());

      if(selectedNum > reportedThemes.size() || selectedNum < 0) {
        System.out.println("잘못된 번호!");
        continue;
      }
      showReportedThemeInfo(reportedThemes,selectedNum);

      break;
    }
    while(true) {
      String isWaring = Prompt.inputString("경고주기 (y/N) > ");
      int count = selectedUser.getWarningCount();

      if(isWaring.equalsIgnoreCase("y")) {
        selectedUser.setWarningCount(++count);
        return;
      } else if (isWaring.equalsIgnoreCase("n")) {
        return;
      } else {
        System.out.println("잘못된 문자!");
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
      if(selectedReportThemeTitle.equals(report.getReportedTheme().getTitle())) {
        System.out.printf("<%s>\n", index++);
        String reportedTitle =report.getReportedTheme().getTitle();
        System.out.println("신고 당한 테마 > " + reportedTitle);
        System.out.println("신고 한 유저 > " + report.getWriter().getNickName());
        System.out.println("신고 내용 > " + report.getContent());
        System.out.println("신고 날짜 > " + report.getRegisteredDate());
        System.out.println("신고 상태 > " + report.getState());
      }
    }

  }
  private User findUserByUserName(String userName) {
    for(User user : userList) {
      if(user.getNickName().equals(userName)) {
        return user;
      }
    }
    return null;
  }
}