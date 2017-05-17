<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/shop/product_detail.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
function cartAdd(){
	
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품 정보</h2>
<table>
  <tr>
    <td><img src="${path}/images/${vo.picture_url}"
    	width="300px" height="300px"></td>
    <td align="center">
      <table>
        <tr>
          <td>상품명</td>
          <td>${vo.product_name}</td>
        </tr>
        <tr>
          <td>가격</td>
          <td>${vo.price}</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>${vo.description}</td>
        </tr>
        <tr>
          <td colspan="2">
          	<form name="form1" method="post"
          	action="${path}/shop/cart/insert.do">
<input type="hidden" name="product_id" 
value="${vo.product_id}">
<select name="amount">
	<c:forEach begin="1" end="10" var="i">
		<option value="${i}">${i}</option>
	</c:forEach>
</select>&nbsp;개
<input type="submit" value="장바구니에 담기">          		
          	</form>
          	<a href="${path}/shop/product/list.do">
          	상품목록	</a>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>

















