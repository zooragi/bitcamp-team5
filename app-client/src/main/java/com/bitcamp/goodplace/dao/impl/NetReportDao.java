package com.bitcamp.goodplace.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.goodplace.dao.ReportDao;
import com.bitcamp.goodplace.domain.Report;
import com.bitcamp.goodplace.domain.ReportTheme;
import com.bitcamp.goodplace.domain.ReportUser;
import com.bitcamp.request.RequestAgent;

public class NetReportDao implements ReportDao{

	RequestAgent requestAgent;
	
	public NetReportDao(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public List<Report> findAll() throws Exception {
		requestAgent.request("report.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
    return new ArrayList<>(requestAgent.getObjects(Report.class));
	}
	
	public List<ReportTheme> findThemeAll() throws Exception {
		requestAgent.request("report.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
    return new ArrayList<>(requestAgent.getObjects(ReportTheme.class));
	}
	
	public List<ReportUser> findUserAll() throws Exception {
		requestAgent.request("report.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
    return new ArrayList<>(requestAgent.getObjects(ReportUser.class));
	}

	@Override
	public void userInsert(ReportUser report) throws Exception {
		requestAgent.request("report.userInsert", report);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
		
	}

	@Override
	public void themeInsert(ReportTheme report) throws Exception {
		requestAgent.request("report.themeInsert", report);
		if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			throw new Exception(requestAgent.getObject(String.class));
		}
		
	}

}