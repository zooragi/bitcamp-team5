package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportAddThemeHandler implements Command{

  List<ReportTheme> reportList;
  List<User> userList;

  public ReportAddThemeHandler(List<User> userList, List<ReportTheme> reportList) {
    this.reportList = reportList;
    this.userList = userList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 신고하기]");
    int count;
    if(reportList.size() == 0) {
      count = 0;
    } else {
      count = reportList.get(reportList.size()-1).getNo();
    }

    ReportTheme reportTheme = new ReportTheme();

    String themeTitle = Prompt.inputString("신고할 테마 이름 > ");

    if(findByTitle(themeTitle) == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    if(findByTitle(themeTitle).getThemeOwnerName()
        .equals(AuthLoginHandler.getLoginUser().getNickName())) {
      System.out.println("본인의 테마 신고 불가!");
      return;
    }

    reportTheme.setReportedTheme(findByTitle(themeTitle));

    System.out.println();

    String content = Prompt.inputString("신고 사유 > ");
    reportTheme.setNo(++count);
    reportTheme.setContent(content);
    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportTheme.setWriter(AuthLoginHandler.getLoginUser());
    reportTheme.setState(Report.WAITING);
    reportList.add(reportTheme);
    System.out.println("테마 신고 완료!");

  }

  private Theme findByTitle(String themeTitle) {
    for(User user : userList) {
      for(Theme theme : user.getThemeList())
        if(theme.getTitle().equals(themeTitle)) {
          return theme;
        }
    }
    return null;
  }

}