package com.welcomeToJeju.moj.dao;

import java.util.List;

import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;

public interface ReportDao {
	void insertreportUser(ReportUser report) throws Exception;
	void insertreportTheme(ReportTheme report) throws Exception;
	List<ReportTheme> themeReportFindByUserNo(int userNo) throws Exception;
	List<ReportTheme> themeReportAll() throws Exception;
	List<ReportUser> userReportFindByUserNo(int userNo) throws Exception;
	List<ReportUser> userReportAll() throws Exception;
}
