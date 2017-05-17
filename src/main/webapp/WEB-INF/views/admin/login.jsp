<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- views/member/login.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(document).ready(function(){
	$("#btnLogin").click(function(){
		// 태그.val()  태그에 입력된 값
		// 태그.val("값")  태그의 값을 변경
		var userid=$("#userid").val();
		var userpw=$("#userpw").val();
		if(userid == ""){
			alert("아이디를 입력하세요");
			$("#userid").focus(); //입력포커스 이동
			return; //함수 종료
		}
		if(userpw==""){
			alert("비밀번호를 입력하세요");
			$("#userpw").focus();
			return;
		}
		//폼 내부의 데이터를 전송할 주소
		document.form1.action=
			"${path}/admin/login_check.do";
		//제출
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>관리자 로그인</h2>
<form name="form1" method="post">
<table border="1" width="400px">
  <tr>
    <td>아이디</td>
    <td><input id="userid" name="userid"></td>
  </tr>
  <tr>
  	<td>비밀번호</td>
  	<td><input type="password" id="userpw" name="userpw"></td>
  </tr>
  <tr>
  	<td colspan="2" align="center">
  		<button type="button" id="btnLogin">로그인
  		</button>
<c:if test="${param.message == 'nologin'}">
	<div style="color:red;">
		먼저 로그인하세요.
	</div>
</c:if>    		
<c:if test="${message == 'error'}">
	<div style="color:red;">
		아이디 또는 비밀번호가 일치하지 않습니다.
	</div>
</c:if>  		
<c:if test="${message == 'logout'}">
	<div style="color:red;">
		로그아웃되었습니다.
	</div>
</c:if>  
  	</td>
  </tr>
</table>
</form>
</body>
</html>























