<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자 맞추기 게임</title>
</head>
<body>
	<h1>숫자 맞추기 게임</h1>
	<hr>
	<h3>${message }</h3>
	
	<c:if test="${sessionScope.count != null}">
		<form method="get" action="guess">
			1부터 100 사이의 숫자를 맞춰주세요. <br>
			<input type="text" name="number">
			<input type="submit" value="확인">
		</form>
	</c:if>
	
	<a href="guess">게임 다시 시작하기</a>
</body>
</html>