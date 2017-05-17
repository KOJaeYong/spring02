<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(document).ready(function(){
	$("#btnUpdate").click(function(){
		document.form1.action="${path}/memo/update/${vo.idx}";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
/* 			document.form1.action="${path}/memo/delete/${vo.idx}";
			document.form1.submit(); */
			location.href="${path}/memo/delete/${vo.idx}";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>메모 보기</h2>
<form method="post" name="form1">
<table border="1" width="500px">
  <tr>
  	<td>번호</td>
  	<td>${vo.idx}</td>
  </tr>
  <tr>
  	<td>이름</td>
  	<td><input name="writer" value="${vo.writer}"></td>
  </tr>
  <tr>
  	<td>메모</td>
  	<td><input name="memo" value="${vo.memo}" size="50"></td>
  </tr>
  <tr align="center">
  	<td colspan="2">
<!-- 수정,삭제를 위한 key value 전송 -->  	
  		<input type="hidden" name="idx" value="${vo.idx}">
  		<button type="button" id="btnUpdate">수정</button>
		<button type="button" id="btnDelete">삭제</button>
  	</td>
  </tr>
</table>
</form>

</body>
</html>


















