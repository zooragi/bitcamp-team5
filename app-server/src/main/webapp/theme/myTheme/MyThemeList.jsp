<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>내 테마 목록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"> </script>
  <style>
  .container {
    xborder : 1px solid red;
    width : 640px;
  }</style>
</head>
<body>
<div class = "container">
<h1>${loginUser.nickname}님의 테마 목록 보기</h1>
<a href='addform' class ="btn btn-outline-primary btn-sm" >새 테마 만들기</a><br>
<table class = "table table-hover">
<thead>
  <tr>
    <th>제목</th>
    <th>카테고리</th>
    <th>해시태그</th>
    <th>조회수</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${myThemeList}" var="theme">
<tr>
<td><a href='detail?no=${theme.no}'>${theme.title}</a></td> 
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
<c:if test='${theme.isPublic ==1}'>
<td>${theme.viewCount}</td> 
</c:if>
<c:if test = '${theme.isPublic==0}'>
<td>비공개테마</td>
</c:if>
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
</body>
</html>

