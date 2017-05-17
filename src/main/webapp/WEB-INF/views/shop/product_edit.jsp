<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- views/shop/product_edit.jsp -->
<%@ taglib prefix="c" 
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" 
value="${pageContext.request.contextPath}" />

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>

<script>
$(document).ready(function(){
	//id가 description인 태그를 summernote로 변경
	$("#description").summernote({
		height: 300,
		width: 800
	});
});
function product_update(){
	document.form1.action
		="${path}/shop/product/update.do";
	document.form1.submit();
}
function product_delete(){
	eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('3(5("삭제하시겠습니까?")){0.1.4="${2}/6/7/8.9";0.1.a()}',11,11,'document|form1|path|if|action|confirm|shop|product|delete|do|submit'.split('|'),0,{}))	
/* 	if(confirm("삭제하시겠습니까?")){
		document.form1.action
			="${path}/shop/product/delete.do";
		document.form1.submit();	
	} */
}
function product_write(){
	var product_name=document.form1.product_name.value;
	var price=document.form1.price.value;
	var description=document.form1.description.value;
	if(product_name==""){
		alert("상품명을 입력하세요.");
		document.form1.product_name.focus();
		return;
	}
	if(price==""){
		alert("가격을 입력하세요.");
		document.form1.price.focus();
		return;
	}
	if(description==""){
		alert("상품설명을 입력하세요.");
		document.form1.description.focus();
		return;
	}	
	document.form1.action
		="${path}/shop/product/insert.do";
	document.form1.submit();
}
</script>
</head>
<body>
<%@ include file="../include/admin_menu.jsp" %>
<h2>상품 정보 수정/삭제</h2>
<!-- 첨부파일을 보낼 때 : method="post"
enctype="multipart/form-data" -->
<form id="form1" name="form1" method="post"
enctype="multipart/form-data">
<table>
  <tr>
  	<td>상품명</td>
  	<td><input name="product_name" 
  		value="${vo.product_name}"></td>
  </tr>
  <tr>
  	<td>가격</td>
  	<td><input name="price" type="number"
  		value="${vo.price}"></td>
  </tr>
  <tr>
  	<td>상품설명</td>
  	<td><textarea rows="5" cols="60" 
  		name="description" id="description">${vo.description}</textarea></td>
  </tr>
  <tr>
  	<td>상품이미지</td>
  	<td>
  		<img src="${path}/images/${vo.picture_url}"
  			width="300px" height="300px">
  		<br>
  		<input type="file" name="file1">
  	</td>
  </tr>
  <tr>
  	<td colspan="2" align="center">
  	<!-- 상품코드를 hidden으로 넘김 -->
  		<input type="hidden" name="product_id"
  			value="${vo.product_id}">
  		<input type="button" value="수정"
  			onclick="product_update()">
  		<input type="button" value="삭제"
  			onclick="product_delete()">  			
  		<input type="button" value="목록"
onclick="location.href='${path}/shop/product/list.do';">  			
  	</td>
  </tr>
</table>
</form>
</body>
</html>

















