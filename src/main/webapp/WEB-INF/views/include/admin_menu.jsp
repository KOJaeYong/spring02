<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- src/main/webapp/WEB-INF/views/include/admin_menu.jsp -->
<a href="${path}/chart/chart1.do">차트1</a> |
<a href="${path}/chart/chart2.do">차트2</a> |
<a href="${path}/pdf/list.do">PDF예제</a> |
<a href="${path}/email/write.do">이메일 발송</a> |
<a href="${path}/shop/product/list.do">상품목록</a> |
<a href="${path}/shop/product/write.do">상품등록</a> | 
<c:choose>
	<c:when test="${sessionScope.admin_userid == null}">
		<a href="${path}/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.admin_username}님이 로그인중입니다.
		<a href="${path}/admin/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
<hr>










