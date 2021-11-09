
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
   <title>회원 가입</title>
<style>
label {
margin-right: 5px;
text-align: right;
display: inline-block;
width: 60px;
}
</style>
</head>
 <body>
<h1>회원 가입</h1>
<form action='add'>
<label for='f-email'>이메일</label>  <input id='f-email' type='email' name='email'><br>
<label for='f-password'>암호</label>  <input id='f-password' type='password' name='password'><br>
<label for='f-nickname'>닉네임</label>  <input id='f-nickname' type='nickname' name='nickname'><br>
<button>가입</button><br>
</form>
</body>
</html>
    