
<%@page import="com.welcomeToJeju.moj.domain.Theme"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.welcomeToJeju.moj.dao.PlaceDao"%>
<%@page import="com.welcomeToJeju.moj.dao.ThemeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>

<html>
<head>
   <title>테마삭제</title>
</head>
 <body>
<h1>테마삭제결과</h1>

<%
String title = request.getParameter("title");
Theme theme = themeDao.findByTitle(title);

if (theme == null) {
  throw new Exception("해당 번호의 테마가 없습니다!");
  
} else {

  themeDao.deleteAllLikedThemeByThemeNo(theme.getNo());
  themeDao.deleteHashtag(theme.getNo());
  themeDao.deletePlaceUserTheme(theme.getNo());
  themeDao.delete(theme.getNo());
  sqlSession.commit();
  response.sendRedirect("list");
%>

  out.println("나의 테마 삭제하기 성공!<br>");

<a href='myThemeList.jsp'>[목록]</a><br>
<%} %>
</body>
</html>

<%!
ThemeDao themeDao;
PlaceDao placeDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
  themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
  placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
  sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
}
%>
