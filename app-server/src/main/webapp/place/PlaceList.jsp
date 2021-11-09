<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장소 목록 보기</title>
</head>

<body>

장소 목록 보기 📄
<table border='1'>
<thead>
<tr>
  <th>번호</th>
  <th>이름</th>
	<th>주소</th>
	<th>위도</th>
	<th>경도</th>
</tr>
</thead>
	
<tbody>
	<c:forEach items="${placeList}" var="place">
	<tr>
    <td>${place.id}</td>
    <td><a href='../place/detail?id=${place.id}'>${place.storeName}</a></td>
    <td>${place.storeAddress}</td>
    <td>${place.xCoord}</td>
    <td>${place.yCoord}</td>
	</tr>
	</c:forEach>
</tbody>
</table>

</body>
</html>