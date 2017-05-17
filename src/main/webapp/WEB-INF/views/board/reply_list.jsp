<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- views/board/reply_list.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<table style="width:700px;">
<c:forEach var="row" items="${list}">   
  <tr>
  	<td>
  		${row.username} 
  		( <fmt:formatDate 
value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/> )
		<br>
		${row.replytext}  		
<!-- 본인의 댓글만 수정,삭제가 가능하도록 처리 -->	
<c:if test="${sessionScope.userid == row.replyer }">
		<input type="button" value="Modify"
			onclick="showModify('${row.rno}')">
</c:if>			
  	</td>
  </tr>
</c:forEach>
<!-- 페이지 나누기  -->  
  <tr>
  	<td>
  		<c:if test="${pager.curBlock > 1}">
<a href="javascript:listReply('${pager.prevPage}')">
  		  [이전]</a>&nbsp;
  		</c:if>
  		<c:forEach var="num" begin="${pager.blockBegin}"
  			end="${pager.blockEnd}">
			<c:choose>
				<c:when test="${num == pager.curPage}">
				<!-- 현재 페이지 -->
					${num}&nbsp;
				</c:when>
				<c:otherwise>
				<!-- 현재 페이지가 아닐 때 -->
					<a href=
"javascript:listReply('${num}')">${num}</a>&nbsp;
				</c:otherwise>
			</c:choose>			  			
  		</c:forEach>
  		<c:if test="${pager.curBlock <= pager.totBlock}">
<a href="javascript:listReply('${pager.nextPage}')">
  		  [다음]</a>&nbsp;
  		</c:if>  		
  	</td>
  </tr>
</table>
</body>
</html>

















