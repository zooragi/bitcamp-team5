package com.welcomeToJeju.moj.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.Report;
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
    int uniqueNum;
    ArrayList<Report> reportList = (ArrayList<Report>) reportDao.findAll();
    
		loop: while (true) {
			uniqueNum = Prompt.inputInt("고유 번호(취소 : 엔터) > ");
			for (Report r : reportList) {
				if (r.getNo() == uniqueNum) {
					System.out.println("존재하는 번호입니다. 다시 입력 하시오.");
					continue loop;
				}
			}
			break;
		}

    ReportUser reportUser = new ReportUser();

    String userNickName = Prompt.inputString("신고할 유저 닉네임 >");
    User ReportedUser = userPrompt.findByName(userNickName);
    
    if (ReportedUser == null) {
      System.out.println("등록된 유저 없음!");
      return;
    }

    if(userNickName.equals(AuthLoginHandler.getLoginUser().getNickname())) {
      System.out.println("본인은 신고 불가!");
      return;
    }
    
    reportUser.setReportedUserName(userNickName);

    System.out.println();

    String content = Prompt.inputString("신고 사유 > ");
    reportUser.setNo(uniqueNum);
    reportUser.setContent(content);
    reportUser.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportUser.setWriter(AuthLoginHandler.getLoginUser());
    reportUser.setState(Report.WAITING);
    
    reportDao.userInsert(reportUser);
    userPrompt.increaseReportedCount(ReportedUser);
    System.out.println("유저 신고 완료!");

  }



}