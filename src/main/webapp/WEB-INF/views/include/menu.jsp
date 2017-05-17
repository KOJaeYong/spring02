<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- src/main/webapp/WEB-INF/views/include/menu.jsp -->
<a href="${path}/memo/list.do">한줄메모장</a> |
<a href="${path}/board/list.do">게시판</a> | 
<a href="${path}/upload/uploadForm">업로드</a> | 
<a href="${path}/upload/uploadAjax">업로드(ajax)</a> | 
<a href="${path}/shop/product/list.do">상품목록</a> | 
<a href="${path}/shop/cart/list.do">장바구니</a> | 

<c:choose>
	<c:when test="${sessionScope.userid == null}">
		<a href="${path}/member/login.do">로그인</a>
		| 
		<a href="${path}/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.username}님이 로그인중입니다.
		<a href="${path}/member/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
<hr>










