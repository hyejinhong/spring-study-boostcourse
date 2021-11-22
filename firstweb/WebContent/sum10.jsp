<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int total = 0;
		for(int i=1; i<=10; i++) {
			total += i;
		}
	%>

	// 모든 JSP는 서블릿으로 바뀌어서 동작한다.
	
	1부터 10까지의 합 : <%=total %>
</body>
</html>