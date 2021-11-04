package com.welcomeToJeju.moj.handler.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.util.Prompt;

public class AdminReportUserProcessHandler implements Command {

  ReportDao reportDao;
  UserDao userDao;
  SqlSession sqlSession;
  public AdminReportUserProcessHandler(ReportDao reportDao,UserDao userDao,SqlSession sqlSession) {
    this.reportDao  = reportDao;
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    List<User> countedUserList = new ArrayList<>();
    List<ReportUser> reportUserList = reportDao.findAllReportUser();
    User selectedUser;
    int index = 1;

    System.out.println("[유저 신고 처리하기]");
    if (reportUserList.size() == 0) {
      System.out.println("유저 신고 없음!");
      return;
    }

    countedUserList = userDao.findAllReportedUser();

    for (User user : countedUserList) {
      System.out.printf("%d. [%d회] %s \n", index++, user.getReportedCount(), user.getNickname());
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

  private void showReportedThemeInfo(List<ReportUser> reportUserList, List<User> countedUserList, int selectedNum) {
    int index = 1;
    String selectedReportUserTitle = countedUserList.get(selectedNum - 1).getNickname();
    for (ReportUser report : reportUserList) {
      if (selectedReportUserTitle.equals(report.getReportedUser().getNickname())) {
        System.out.printf("(%s)\n", index++);
        String reportedName = report.getReportedUser().getNickname();
        System.out.println("신고 당한 유저 : " + reportedName);
        System.out.println("신고 한 유저 : " + report.getWriter().getNickname());
        System.out.println("신고 내용 : " + report.getContent());
        System.out.println("신고 날짜 : " + report.getRegisteredDate());
        System.out.println("신고 상태 : " + report.getStatus().getTitle());
      }
    }
  }
}