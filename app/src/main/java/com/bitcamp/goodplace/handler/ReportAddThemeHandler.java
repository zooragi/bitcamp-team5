package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;

import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportAddThemeHandler implements Command{
  
	List<Report> reportList;
  List<User> userList;
	
	public ReportAddThemeHandler(List<User> userList, List<Report> reportList) {
    this.reportList = reportList;
    this.userList = userList;
  }
  
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 신고]");
    int count;
    if(reportList.size() == 0) {
    	count = 0;
    } else {
    	count = reportList.get(reportList.size()-1).getNo();
    }
    
    ReportTheme reportTheme = new ReportTheme();
   
    String themeTitle = Prompt.inputString("신고할 테마의 이름을 입력해주세요");
    if(findByTitle(themeTitle) == null) {
    	System.out.println("해당하는 테마가 없습니다");
    	return;
    }
    reportTheme.setTheme(findByTitle(themeTitle));
    
    System.out.println();

    String content = Prompt.inputString("신고 사유를 기입해주세요");
    reportTheme.setNo(++count);
    reportTheme.setContent(content);
    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportTheme.setWriter(AuthLoginHandler.getLoginUser());
    reportTheme.setState(Report.WAITING);
    reportList.add(reportTheme);
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
