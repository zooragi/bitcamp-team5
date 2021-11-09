<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>장소 상세 보기</title>
</head>

<body>
<h1>장소 상세 보기(MVC + EL)</h1>

장소 상세 보기 🔍
<form action='PlaceUpdate.jsp'>
  <label for='f-id'>번호</label>
  <input id='f-id' type='text' name='id' value='${place.id}' readonly><br>
  
  <label for='f-storeName'>이름</label>
  <input id='f-storeName' type='text' name='storeName' value='${place.storeName}' readonly><br>
  
  <label for='f-storeAddress'>주소</label>
  <input id='f-storeAddress' type='text' name='storeAddress' value='${place.storeAddress}' readonly><br>
  
  <label for='f-xCoord'>위도</label>
  <input id='f-xCoord' type='text' name='xCoord' value='${place.xCoord}' readonly><br>
  
  <label for='f-yCoord'>경도</label>
  <input id='f-yCoord' type='text' name='yCoord' value='${place.yCoord}' readonly><br>
  
  <label for='f-photos'>사진</label>
  <input id='f-photos' type='text' name='photos' value='${place.photos}'><br>
  
  <label for='f-comments'>댓글</label>
  <input id='f-comments' type='text' name='comments' value='${place.comments}'><br>
  
  <button>변경</button>
  <a href='delete?id=${place.id}'>[삭제]</a>
  <a href='list?no=${theme.no}'>[목록]</a><br>
</form>

</body>
</html>
