package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportAddUserHandler implements Command {

  List<ReportUser> reportList;
  List<User> userList;

  public ReportAddUserHandler(List<User> userList, List<ReportUser> reportList) {
    this.reportList = reportList;
    this.userList = userList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[유저 신고하기]");
    int count;
    if (reportList.size() == 0) {
      count = 0;
    } else {
      count = reportList.get(reportList.size() - 1).getNo();
    }

    ReportUser reportUser = new ReportUser();

    String userNickName = Prompt.inputString("신고할 유저 닉네임 >");
    if (findByName(userNickName) == null) {
      System.out.println("등록된 유저 없음!");
      return;
    }

    if(userNickName.equals(AuthLoginHandler.getLoginUser().getNickName())) {
      System.out.println("본인은 신고 불가!");
      return;
    }

    reportUser.setReportedUser(findByName(userNickName));

    System.out.println();

    String content = Prompt.inputString("신고 사유 > ");
    reportUser.setNo(++count);
    reportUser.setContent(content);
    reportUser.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportUser.setWriter(AuthLoginHandler.getLoginUser());
    reportUser.setState(Report.WAITING);
    reportList.add(reportUser);
    System.out.println("유저 신고 완료!");

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