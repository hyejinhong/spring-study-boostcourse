<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
hello~~
<%
	System.out.println("jspService()");
%>

<%!
	public void jspDestroy() {
	System.out.println("jspDestroy()");
}
%>
<%! 
	public void jspInit() {
	System.out.println("jspInit()");
}
%>

</body>
</html>