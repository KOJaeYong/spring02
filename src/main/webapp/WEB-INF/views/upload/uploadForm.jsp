<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style>
iframe {
	width : 400px;
	height : 200px;
	border : 1px;
	border-style: solid; 
}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<!-- p.523 -->
<!-- enctype="multipart/form-data" 파일 업로드 필수 옵션
enctype="application/x-www-form-urlencoded" 기본 옵션 -->
<!-- target="iframe의 name" => iframe으로 전송 -->
<form id="form1" action="${path}/upload/uploadForm" method="post"
	enctype="multipart/form-data"
	target="iframe1" >
<input type="file" name="file">
<input type="submit" value="업로드">
</form>
<!-- hidden frame -->
<iframe name="iframe1"></iframe>
<script>
function addFilePath(msg){
	alert(msg);
	console.log(msg);
	document.getElementById("form1").reset();
}
</script>

</body>
</html>

















