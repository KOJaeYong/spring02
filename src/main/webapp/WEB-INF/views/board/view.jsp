<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script type="text/javascript" 
src="${path}/include/js/common.js"></script>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//첨부파일 드래그 이벤트
	$("#fileDrop").on("dragenter dragover",function(e){
		e.preventDefault(); //기본효과 막음
	});
	$("#fileDrop").on("drop",function(e){
		e.preventDefault(); //기본 효과 막음
		//드래그한 파일들
		var files=e.originalEvent.dataTransfer.files;
		//console.log(files);
		var file=files[0]; //첫번째 첨부파일
		var formData=new FormData(); //폼 데이터 객체
		formData.append("file",file); //첨부파일 추가
// processData: false : header가 아닌 body로 전달		
		$.ajax({
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			type: "post",
			success: function(data){
console.log(data);
//첨부파일의 정보
var fileInfo=getFileInfo(data);
//하이퍼링크
var html="<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a><br>";
//hidden 태그 추가
html+="<input type='hidden' class='file' value='"+fileInfo.fullName+"'>";   
//div에 추가
$("#uploadedList").append(html);
			}
		});
	});
	
	//첨부파일 목록 불러오기
	listAttach();
	//listReply("1"); //댓글 목록 불러오기
	listReply_rest("1"); //댓글 목록(rest방식)
	//listReply2(); //json 리턴 방식
// 태그.on("이벤트","자손태그",이벤트핸들러)
	//파일 삭제 처리
	$("#uploadedList").on(
			"click",".file_del",function(e){
		var that=$(this); //클릭한 a 태그
		$.ajax({
			type: "post",
			url: "${path}/upload/deleteFile",
			data: {fileName:$(this).attr("data-src")},
			dataType: "text",
			success: function(result){
				if( result == "deleted"){
					that.parent("div").remove();
				}
			}
		});
	});
	
	//댓글 쓰기 버튼
	$("#btnReply").click(function(){
		//reply(); //폼데이터로 입력
		reply_json();  //json으로 입력
	});
	
	$("#btnList").click(function(){
		location.href=
"${path}/board/list.do?curPage=${curPage}"
+"&search_option=${search_option}"
+"&keyword=${keyword}";
	});
	
	$("#btnDelete").click(function(){
/* 		var count="${count}"; //댓글의 갯수
		if(count > 0){
			alert(
		"댓글이 있는 게시물은 삭제할 수 없습니다.");
			return; //함수 종료
		} */
		if(confirm("삭제하시겠습니까?")){
			document.form1.action=
				"${path}/board/delete.do";
			document.form1.submit();
		}
	});
	
	$("#btnUpdate").click(function(){
//		var writer=document.form1.writer.value;
		var content=document.form1.content.value;
		var title=document.form1.title.value;
/* 		if(writer==""){
			alert("이름을 입력하세요");
			document.form1.writer.focus();
			return;
		} */
/* 		if(content==""){
			alert("내용을 입력하세요");
			document.form1.content.focus();
			return;
		}
 */		if(title==""){
			alert("제목을 입력하세요");
			document.form1.title.focus();
			return;
		}		
		//수정 주소
		document.form1.action="${path}/board/update.do";
		var str="";
		// id가 uploadedList인 태그 내부에 있는 hidden 태그들		
$("#uploadedList .file").each(function(i){
		str+=
		"<input type='hidden' name='files["+i+"]' value='"
		+$(this).val()+"'>";
				});
				//폼에 hidden 태그들을 추가
				$("#form1").append(str); 
		//폼에 입력한 데이터를 서버로 전송함
		document.form1.submit();
	});
});
// $(객체) $("태그")  $("#id") $(".class")
function listAttach(){
	$.ajax({
		type: "post",
		url: "${path}/board/getAttach/${dto.bno}",
		success: function(list){
			$(list).each(function(){
// each문 내부의 this : 각 step에 해당되는 값
/* href="#" null link, 하이퍼링크로 이동하지 않음 */
var fileInfo=getFileInfo(this);
var html="<div><a href='"+fileInfo.getLink+"'>"
	             +fileInfo.fileName+"</a>&nbsp;&nbsp;";
html+="<a href='#' class='file_del' data-src='"+this+"'>[삭제]</a></div>";	             
$("#uploadedList").append(html);
			});
		}
	});
}

function reply_json(){
	var replytext=$("#replytext").val();
	var bno="${dto.bno}";
	//비밀댓글 체크 여부
	var secret_reply="n";
//태그.is(":checked") 체크여부 true/false		
	if( $("#secret_reply").is(":checked")){
		secret_reply="y";
	}
	$.ajax({
		type: "post",
		url: "${path}/reply/insert_rest.do",
		headers: {
			"Content-Type" : "application/json"
		},
		dataType: "text",
		data: JSON.stringify({
			bno : bno,
			replytext : replytext,
			secret_reply : secret_reply
		}),
		success: function(){
			alert("댓글이 등록되었습니다.");
			//listReply2();
			//listReply("1");
			listReply_rest("1"); //댓글 목록(rest방식)
		}
	});
}
function reply(){
	var replytext=$("#replytext").val();
	var bno="${dto.bno}";
	//비밀댓글 체크 여부
	var secret_reply="n";
//태그.is(":checked") 체크여부 true/false		
	if( $("#secret_reply").is(":checked")){
		secret_reply="y";
	}
	//alert(secret_reply);
	var param
		="replytext="+replytext
		+"&bno="+bno
		+"&secret_reply="+secret_reply;
	$.ajax({
		type: "post",
		url: "${path}/reply/insert.do",
		data: param,
		success: function(){
			alert("댓글이 등록되었습니다.");
			//listReply2();
			listReply("1");
		}
	});
}
function listReply(num){
	$.ajax({
		type: "get",
		url: 
"${path}/reply/list.do?bno=${dto.bno}&curPage="+num,
		success: function(result){
// responseText가 result에 저장됨			
			$("#listReply").html(result);
		}
	});
}
function listReply_rest(num){
	$.ajax({
		type: "get",
		url: "${path}/reply/list/${dto.bno}/"+num,
		success: function(result){
// responseText가 result에 저장됨			
			$("#listReply").html(result);
		}
	});
}
function listReply2(){
//contentType: "application/json", 생략가능	
	$.ajax({
		type: "get",
		contentType: "application/json",
		url: "${path}/reply/list_json.do?bno=${dto.bno}",
		success: function(result){
			//console.log(result);
			var output="<table>";
			for( var i in result ) {
				output+="<tr>";
				output+="<td>"+result[i].username;
		  		output+="( "
+changeDate(result[i].regdate) +")<br>";
		  		output+=result[i].replytext+"</td>";
		  		output+="</tr>";
			}
			output+="</table>";
			$("#listReply").html(output);
		}
	});
}
function changeDate(date){
	date=new Date(parseInt(date));
	year=date.getFullYear();
	month=date.getMonth();
	day=date.getDate();
	hour=date.getHours();
	minute=date.getMinutes();
	second=date.getSeconds();
	strDate = year+"-"+month+"-"+day+" "+hour+":"
		+minute+":"+second;
	return strDate;
}
function showModify(rno){
	$.ajax({
		type: "get",
		url: "${path}/reply/detail/"+rno,
		success: function(result){
			$("#modifyReply").html(result);
			// 태그.css("속성","값")	
			$("#modifyReply").css(
					"visibility","visible");
		}
	});
}
</script>
<style>
#modifyReply {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute; 
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 10;
	visibility: hidden;
}
#fileDrop {
	width: 600px;
	height: 100px;
	border: 1px solid gray;
	background-color: gray;
}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<c:choose>
  <c:when test="${dto.show == 'y' }">
<h2>게시물 보기</h2>
<form id="form1" name="form1" method="post">
<div>
	작성일자 : 
		<fmt:formatDate value="${dto.regdate}"
		pattern="yyyy-MM-dd a HH:mm:ss"/>
<!-- 날짜형식 yyyy 4자리 연도 MM 월 dd 일 a 오전/오후
		HH 24시간제 hh 12시간제 mm 분 ss 초 -->		  
</div>
<div>
	조회수 : ${dto.viewcnt}
</div>
<div>
	제목
	<input name="title" id="title"
		value="${dto.title}"
		size="80" placeholder="제목을 입력하세요">	
</div>
<div>
	내용
	<textarea id="content" name="content" rows="3" cols="80"
	placeholder="내용을 입력하세요">${dto.content}</textarea>
<script>
CKEDITOR.replace("content");
</script>	
</div>
<div>
	이름 : ${dto.username}
</div>

<!-- 첨부파일 목록 -->
<div id="uploadedList"></div>
<!-- 첨부파일을 드래그할 영역 -->
<div id="fileDrop"></div>


<div style="width:700px; text-align: center;">
<!-- 게시물번호를 hidden으로 처리 -->
	<input type="hidden" name="bno" value="${dto.bno}">
<!-- 본인의 게시물만 수정,삭제가 가능하도록 처리 -->	
<c:if test="${sessionScope.userid == dto.writer }">	
	<button type="button" id="btnUpdate">수정</button>
	<button type="button" id="btnDelete">삭제</button>
</c:if>	
	<button type="button" id="btnList">목록</button>
</div>
</form>

<div style="width:700px; text-align: center;">
	<br>
	<c:if test="${sessionScope.userid != null }">	
		<textarea rows="5" cols="80" id="replytext"
placeholder="댓글을 작성하세요"></textarea>
		<br>
		<input type="checkbox" 
			id="secret_reply">비밀댓글
		<button type="button" id="btnReply">
			댓글쓰기</button>
	</c:if>
</div>
  </c:when>
  <c:otherwise>
		삭제된 게시물입니다.
  </c:otherwise>
</c:choose>



<!-- 댓글 목록 출력 -->
<div id="listReply"></div>
<!-- 댓글 수정 화면 -->
<div id="modifyReply">댓글 수정 화면 영역</div>


</body>
</html>

















