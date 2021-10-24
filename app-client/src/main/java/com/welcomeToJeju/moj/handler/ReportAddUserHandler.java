package com.welcomeToJeju.moj.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportStatus;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.util.Prompt;

public class ReportAddUserHandler implements Command {

	ReportDao reportDao;
	UserPrompt userPrompt;
	
  public ReportAddUserHandler(ReportDao reportDao,UserPrompt userPrompt) {
  	this.reportDao = reportDao;
  	this.userPrompt = userPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[유저 신고하기]");

    ReportUser reportUser = new ReportUser();

    String userNickName = Prompt.inputString("신고할 유저 닉네임 >");
    User reportedUser = userPrompt.findByName(userNickName);
    
    if (reportedUser == null) {
      System.out.println("등록된 유저 없음!");
      return;
    }

    if(userNickName.equals(AuthLoginHandler.getLoginUser().getNickname())) {
      System.out.println("본인은 신고 불가!");
      return;
    }
    
    reportUser.setReportedUser(reportedUser);

    System.out.println();
    ReportStatus reportStatus = new ReportStatus();
    reportStatus.setNo(100);

    String content = Prompt.inputString("신고 사유 > ");
    reportUser.setContent(content);
    reportUser.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportUser.setWriter(AuthLoginHandler.getLoginUser());
    reportUser.setState(reportStatus);
    
    reportDao.reportUserInsert(reportUser);
//    userPrompt.increaseReportedCount(reportedUser);
    System.out.println("유저 신고 완료!");

  }



}