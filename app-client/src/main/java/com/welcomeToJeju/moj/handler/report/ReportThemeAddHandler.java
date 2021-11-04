package com.welcomeToJeju.moj.handler.report;

import java.sql.Date;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.ReportStatus;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class ReportThemeAddHandler implements Command{

  ReportDao reportDao;
  ThemeDao themeDao;
  SqlSession sqlSession;
  public ReportThemeAddHandler(ReportDao reportDao,ThemeDao themeDao,SqlSession sqlSession) {
    this.reportDao = reportDao;
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 신고하기]");
    int uniqueNum;

    ReportTheme reportTheme = new ReportTheme();

    String themeTitle = Prompt.inputString("신고할 테마 이름 > ");
    Theme reportedTheme = themeDao.findByTitle(themeTitle);

    if(reportedTheme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    if(reportedTheme.getOwner().getNo()
        == (AuthLoginHandler.getLoginUser().getNo())) {
      System.out.println("본인의 테마 신고 불가!");
      return;
    }

    reportTheme.setReportedTheme(reportedTheme);

    System.out.println();
    ReportStatus reportStatus = new ReportStatus();
    reportStatus.setNo(100);

    String content = Prompt.inputString("신고 사유 > ");
    reportTheme.setContent(content);
    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));
    reportTheme.setWriter(AuthLoginHandler.getLoginUser());
    reportTheme.setStatus(reportStatus);

    reportDao.insertReportTheme(reportTheme);

    int count = reportedTheme.getReportedCount() + 1;
    HashMap<String,Object> params = new HashMap<>();
    params.put("themeNo", reportedTheme.getNo());
    params.put("reportedCnt", count);
    themeDao.updateReportedCount(params);
    sqlSession.commit();

    System.out.println("테마 신고 완료!");


  }

}