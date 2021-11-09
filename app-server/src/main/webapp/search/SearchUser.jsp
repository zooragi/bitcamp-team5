<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>유저 검색 결과</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"> </script>
  <style>
  .container {
    xborder : 1px solid red;
    width : 800px;
  }</style>
</head>
<body>
<div class = "container">
<h1>유저 목록 보기</h1>
<table class = "table table-hover">
<thead>
  <tr>
    <th>닉네임</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${userList}" var="user">
<tr>
<td><a href='../mytheme/list?no=${user.no}'>${user.nickname}님의 테마 목록</a></td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
</body>
</html>