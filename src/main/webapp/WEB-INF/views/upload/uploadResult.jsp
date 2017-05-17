<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%//@ include file="../include/menu.jsp" %>
<!-- uploadResult.jsp -->
파일이 업로드되었습니다.<br>
파일명 : ${savedName} 
<script>
var result="${savedName}";
parent.addFilePath(result);
</script>
<!-- <script>
var result="${savedName}";
parent.addFilePath(result);
</script> -->

</body>
</html>

















