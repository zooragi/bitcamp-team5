package com.bitcamp.goodplace.dao;

import java.util.List;

import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.ReportUser;

public interface ReportDao {
	void userInsert(ReportUser report) throws Exception;
	void themeInsert(ReportTheme report) throws Exception;
	List<Report> findAll() throws Exception;
	List<ReportTheme> findThemeAll() throws Exception;
	List<ReportUser> findUserAll() throws Exception;
}
