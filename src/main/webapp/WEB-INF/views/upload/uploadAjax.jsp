<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- views/upload/uploadAjax.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}
small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
<script>
$(document).ready(function(){
// # : id , . : class
// 태그.on("이벤트", "자손태그", 이벤트핸들러 )
// data: "fileName="+$(this).attr("data-src")
// 태그.attr("속성")
// $("#userid").attr("type")
 	$(".uploadedList").on("click","span",function(event){
		var that=$(this); //클릭한 span 태그
		$.ajax({
			url: "${path}/upload/deleteFile",
			type: "post",
			data: {fileName:$(this).attr("data-src")},
			dataType: "text",
			success: function(result){
				if( result == "deleted") {
		// 클릭한 span 태그가 속한 div를 제거					
					that.parent("div").remove();
				}
			}
		});
	});
	// 클래스가 fileDrop인 태그
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault(); //기본 효과를 막음
	});
// event : jquery의 이벤트
// originalEvent : javascript의 이벤트
	$(".fileDrop").on("drop",function(event){
		//기본 효과를 막음
		event.preventDefault();
		//드래그된 파일 정보
		var files=event.originalEvent.dataTransfer.files;
		//첫번째 파일
		var file=files[0];
		console.log(file);
		//ajax로 전달할 폼 객체
		var formData = new FormData();
		//폼 객체에 파일 추가, append("변수명",값)
		formData.append("file",file);
// processData:false=> post방식
// contentType: false=> multipart/form-data		
		$.ajax({
			type: "post",
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			success: function(data){
				//alert(data);
console.log(data);
var str="";
if(checkImageType(data)){ //이미지 파일일 경우
	str= 
"<div><a href='${path}/upload/displayFile?fileName="
+getImageLink(data)+"'>";
	str+=
"<img src='${path}/upload/displayFile?fileName="
			 +data+"'></a>";
}else{ //이미지가 아닐 경우
	str= "<div>"
	str+=
	 "<a href='${path}/upload/displayFile?fileName="
	 +data+"'>"+getOriginalName(data)+"</a>";
}
str+= "<span data-src="+data+">[삭제]</span></div>";
$(".uploadedList").append(str);
			}
		});
	});	
});
function getOriginalName(fileName){
	if( checkImageType(fileName)){
		return;
	}
	//uuid를 제외한 원래 파일 이름을 리턴
	var idx=fileName.indexOf("_")+1;
	return fileName.substr(idx);
}
function getImageLink(fileName){
	if(!checkImageType(fileName)){ //이미지 형식이 아니면
		return; //함수 종료
	}
	//이미지 파일이면
//   /2016/11/29/s_05ad4ecf-475a-4463-840c-50476423d9d4_Jellyfish.jpg	
	var front=fileName.substr(0,12); //연월일 경로 추출
	var end=fileName.substr(14); //s_ 제거
	console.log(front);
	console.log(end);
	return front+end;
}
function checkImageType(fileName){
// i : ignore case(대소문자 무관)	
	var pattern= /jpg|gif|png|jpeg/i; //정규표현식
	return fileName.match(pattern); //규칙에 맞으면 true
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<!-- p.536 -->
<h2>Ajax File Upload</h2>
<!-- 파일을 업로드할 영역 -->
<div class="fileDrop"></div>
<!-- 업로드된 파일 목록 -->
<div class="uploadedList"></div>

</body>
</html>


















