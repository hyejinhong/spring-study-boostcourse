<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	pageContext.setAttribute("p1", "page scope's value");
	request.setAttribute("r1", "request scope's value");
	session.setAttribute("s1", "session scope's value");
	application.setAttribute("a1", "application scope's value");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
pageContext.getAttribute("p1") : <%=pageContext.getAttribute("p1")%> <br>
pageContext.getAttribute("p1") : ${pageScope.p1} <br>

request.getAttribute("r1") : <%=request.getAttribute("r1")%> <br>
request.getAttribute("r1") : ${requestScope.r1} <br>

session.getAttribute("s1") : <%=session.getAttribute("s1")%> <br>
session.getAttribute("s1") : ${sessionScope.s1} <br>

application.getAttribute("a1") : <%=application.getAttribute("a1")%> <br>
application.getAttribute("a1") : ${applicationScope.a1} <br>

</body>
</html>