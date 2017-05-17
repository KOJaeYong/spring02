<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" 
content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="include/header.jsp" %>
</head>
<body>
<%@ include file="include/menu.jsp" %>
<c:if test="${message == 'success' }">
<h2>
	${sessionScope.username}(${sessionScope.userid})님
 	환영합니다.
</h2>
</c:if>

<P>  The time on the server is ${serverTime}. </P>
${result}
</body>
</html>

















