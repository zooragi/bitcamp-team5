package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;

public interface ReportDao {

  void insertReportTheme(ReportTheme reportTheme) throws Exception;
  List<ReportTheme> findAllReportTheme() throws Exception;
  List<ReportTheme> reportThemeFindByUserNo(int themeNo) throws Exception;

  void insertReportUser(ReportUser reportUser) throws Exception;
  List<ReportUser> findAllReportUser() throws Exception;
  List<ReportUser> reportUserFindByUserNo(int userNo) throws Exception;


}
