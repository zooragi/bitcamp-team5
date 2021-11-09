<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>${pageTitle}</title>
  <link rel="stylesheet" href="${contextRoot}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextRoot}/css/common.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
  
  <script defer src="https://kit.fontawesome.com/a340a3bb10.js" crossorigin="anonymous"></script>
  <script src = "${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"> </script>
  <script defer src="${contextRoot}/javascript/common.js"></script>
</head>
<body>

<jsp:include page="/header.jsp"/>
<jsp:include page="/sideBar.jsp"/>

<jsp:include page="${contentUrl}"/>

</body>
</html>