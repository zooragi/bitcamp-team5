package com.welcomeToJeju.moj.dao;

import java.util.List;

import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;

public interface ReportDao {
	void userInsert(ReportUser report) throws Exception;
	void themeInsert(ReportTheme report) throws Exception;
	List<Report> findAll() throws Exception;
	List<ReportTheme> findThemeAll() throws Exception;
	List<ReportUser> findUserAll() throws Exception;
}
