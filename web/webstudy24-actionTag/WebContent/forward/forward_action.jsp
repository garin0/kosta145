<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward test</title>
</head>
<body>
<%
	int age =Integer.parseInt(request.getParameter("age"));
	if(age<19){
%>	<%= age %> 세 미성년입니다.
<%}else{ %>
	<%--adult.jsp 로 forward한다. --%>
	<jsp:forward page="adult.jsp">
		<jsp:param value="test" name="command"/>
	</jsp:forward>
<%} %>
</body>
</html>