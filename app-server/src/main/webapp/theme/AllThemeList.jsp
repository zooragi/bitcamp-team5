<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>
  .container {
    width : 800px;
  }</style>
</head>

<div class = "container">
<h1>테마 목록 보기</h1>
<a href='../mytheme/addform' class ="btn btn-outline-primary btn-sm" >새 테마 만들기</a><br>
<table class = "table table-hover">
<thead>
  <tr>
    <th>제목</th>
    <th>테마 만든이</th>
    <th>카테고리</th>
    <th>해시태그</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${allThemeList}" var="theme">
<tr>
<td><a href='../mytheme/detail?no=${theme.no}'>${theme.title}</a></td> 
<td>${theme.owner.nickname}</td>
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
