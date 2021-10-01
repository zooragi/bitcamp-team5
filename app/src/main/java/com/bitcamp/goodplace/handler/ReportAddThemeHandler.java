package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;

import com.bitcamp.goodplace.App;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportAddThemeHandler implements Command{
  
	List<ReportTheme> reportThemeList;
  List<User> userList;
	
	public ReportAddThemeHandler(List<User> userList, List<ReportTheme> reportThemeList) {
    this.reportThemeList = reportThemeList;
    this.userList = userList;
  }
  
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 신고]");
    int count;
    if(reportThemeList.size() == 0) {
    	count = 0;
    } else {
    	count = reportThemeList.get(reportThemeList.size()-1).getNo();
    }
    
    ReportTheme reportTheme = new ReportTheme();
   
    String themeTitle = Prompt.inputString("신고할 테마의 이름을 입력해주세요");
    
    if(findByTitle(themeTitle) == null) {
    	System.out.println("해당하는 테마가 없습니다");
    	return;
    }
    if(findByTitle(themeTitle).getUserName().equals(AuthLoginHandler.getLoginUser().getNickName())) {
    	System.out.println("자신의 테마를 신고할 수 없습니다.");
    	return;
    }
    
    reportTheme.setThemeTitle(findByTitle(themeTitle).getTitle());
    
    System.out.println();

    String content = Prompt.inputString("신고 사유를 기입해주세요");
    reportTheme.setNo(++count);
    reportTheme.setContent(content);
    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportTheme.setWriter(AuthLoginHandler.getLoginUser());
    reportTheme.setState(Report.WAITING);
		count = findByTitle(themeTitle).getReportedCount();
		findByTitle(themeTitle).setReportedCount(++count);
    reportThemeList.add(reportTheme);
    App.reportList.add(reportTheme);
    System.out.println("테마 신고가 완료되었습니다.");

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
