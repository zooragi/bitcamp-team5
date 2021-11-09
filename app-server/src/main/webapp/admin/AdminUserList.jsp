<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
   <title>회원목록</title>
</head>
 <body>
<h1>회원 목록(MVC)</h1>
<table border = '1'>
<thead>
<tr>
<th>번호</th>
<th>닉네임</th>
<th>이메일</th>
<th>등록일</th>
<th>경고</th>
<th>상태</th>
</tr>
</thead>
<tbody>

<c:forEach items="${userList}" var="user">

<tr>
    <td>${user.no}</td>
    <td><a href='detail?no=${user.no}'>${user.nickname}</a></td>
    <td>${user.email}</td>
    <td>${user.registeredDate}</td>
    <td>${user.warningCount}</td>
    <td>
      <c:choose>
        <c:when test="${user.active eq '1'}">
          회원
        </c:when>
        <c:when test="${user.active eq '0'}">
          탈퇴
        </c:when>
      </c:choose>
    </td>    

</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>

