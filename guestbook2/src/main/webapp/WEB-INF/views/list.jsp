<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>방명록</h1>
	<br>방명록 갯수: ${count}
	<br>방문한 수 : ${cookieCount}
	<hr>
	<br>
	
	<c:forEach items="${list}" var="guestbook">
		${guestbook.id} <br>
		${guestbook.name} <br>
		${guestbook.content} <br>
		${guestbook.regdate} <br>
		<c:if test="${sessionScope.isAdmin == 'true'}">
			<a href="delete?id=${guestbook.id}">삭제</a> <br><br>
		</c:if>
		<hr>
	</c:forEach>
	
	<br><br>
	
	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="list?start=${pageIndex}">${status.index + 1}</a>&nbsp;&nbsp;
	</c:forEach>
	
	<br><br>
	
	<form method="post" actions="/write">
		이름: <input type="text" name="name">
		<textarea name="content" rows="6" cols="60"></textarea>
		<br><input type="submit" value="등록">
	</form>
</body>
</html>