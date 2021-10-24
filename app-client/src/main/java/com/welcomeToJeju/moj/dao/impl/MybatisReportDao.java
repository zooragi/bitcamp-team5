package com.welcomeToJeju.moj.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.domain.Report;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.ReportUser;

public class MybatisReportDao implements ReportDao{
	
  SqlSession sqlSession;

  public MybatisReportDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }
  
	@Override
	public List<Report> findAll() throws Exception {
		return null;
	}
	
	@Override
	public List<ReportTheme> themeReportFindByUserNo(int userNo) throws Exception {
		return sqlSession.selectList("ReportMapper.themeReportFindByUserNo", userNo);
	}
	
	@Override
	public List<ReportUser> userReportFindByUserNo(int userNo) throws Exception {
		return sqlSession.selectList("ReportMapper.userReportFindByUserNo", userNo);
	}

	@Override
	public void reportUserInsert(ReportUser report) throws Exception {
		sqlSession.insert("ReportMapper.reportUserInsert", report);
		sqlSession.commit();
	}

	@Override
	public void reportThemeInsert(ReportTheme report) throws Exception {
		sqlSession.insert("ReportMapper.reportThemeInsert", report);
		sqlSession.commit();
	}
  
}
