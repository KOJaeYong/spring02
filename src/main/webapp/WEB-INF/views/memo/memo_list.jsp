<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
function memo_view(idx){
//http://localhost:8080/spring02/memo/view/7 => PathVariable	
	location.href="${path}/memo/view/"+idx
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>한줄 메모장</h2>
<!-- 메모 입력폼 -->
<form method="post" action="${path}/memo/insert.do">
	이름 : <input name="writer" size="10">
	메모 : <input name="memo" size="40">
<!-- 	<button>확인</button> -->
	<input type="submit" value="확인">
</form>

<table border="1" style="width:500px;">
  <tr>
  	<th>번호</th>
  	<th>이름</th>
  	<th>메모</th>
  	<th>날짜</th>
  </tr>
<c:forEach var="row" items="${list}">
  <tr>
  	<td>${row.idx}</td>
  	<td>${row.writer}</td>
  	<td>
<a href="#" onclick="memo_view('${row.idx}')">${row.memo}</a></td>
  	<td><fmt:formatDate value="${row.post_date}"
  		pattern="yyyy-MM-dd HH:mm:ss"/> 
  	</td>
  </tr>
</c:forEach>  
</table>
</body>
</html>

















