<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", -1);
%>    
<!-- src/main/webapp/WEB-INF/views/include/header.jsp -->
<!-- 태그라이브러리 선언 -->
<%@ taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" 
value="${pageContext.request.contextPath}" />
<!-- jquery  -->
<script src="http://code.jquery.com/jquery-3.1.1.min.js">
</script>
<link rel="stylesheet" href="${path}/include/style.css" />


















