package com.welcomeToJeju.moj.handler.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AdminReportThemeProcessHandler implements Command {

  ReportDao reportDao;
  ThemeDao themeDao;
  UserDao userDao;
  SqlSession sqlSession;
  public AdminReportThemeProcessHandler(ReportDao reportDao, ThemeDao themeDao,UserDao userDao,SqlSession sqlSession) {
    this.reportDao = reportDao;
    this.themeDao = themeDao;
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    List<Theme> countedThemeList = new ArrayList<>();
    List<ReportTheme> reportThemeList = reportDao.findAllReportTheme();
    User selectedUser;
    int index = 1;

    System.out.println("[테마 신고 처리하기]");
    if(reportThemeList.size()==0) {
      System.out.println("테마 신고 없음!");
      return;
    }

    countedThemeList = themeDao.findAllReportedTheme();

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

      selectedUser = userDao.findByNo(countedThemeList.get(selectedNum-1).getOwner().getNo()); 

      showReportedThemeInfo(reportThemeList,countedThemeList,selectedNum);

      break;
    }

    while(true) {
      String isWaring = Prompt.inputString("경고주기 (y/N) > ");

      if(isWaring.equalsIgnoreCase("y")) {
        int count = selectedUser.getWarningCount() + 1;
        HashMap<String,Object> params = new HashMap<>();
        params.put("userNo", selectedUser.getNo());
        params.put("warnedCnt", count);
        userDao.updateWarnedCount(params);
        break;
      } else if (isWaring.equalsIgnoreCase("n")) {
        return;
      } else {
        System.out.println("잘못된 문자!");
        continue;
      }
    }
    sqlSession.commit();

  }

  private void showReportedThemeInfo(List<ReportTheme> reportThemeList, List<Theme> countedThemeList, int selectedNum) {
    int index = 1;
    String selectedReportThemeTitle = countedThemeList.get(selectedNum-1).getTitle();
    for (ReportTheme report : reportThemeList) {
      if(selectedReportThemeTitle.equals(report.getReportedTheme().getTitle())) {
        System.out.printf("<%s>\n", index++);
        String reportedTitle =report.getReportedTheme().getTitle();
        System.out.println("신고 당한 테마 > " + reportedTitle);
        System.out.println("신고 한 유저 > " + report.getWriter().getNickname());
        System.out.println("신고 내용 > " + report.getContent());
        System.out.println("신고 날짜 > " + report.getRegisteredDate());
        System.out.println("신고 상태 > " + report.getStatus().getTitle());
      }
    }

  }
}