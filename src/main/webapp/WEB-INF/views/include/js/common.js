/*views/include/js/common.js*/
//이미지 파일 여부 판단
function checkImageType(fileName){
	var pattern= /jpg|gif|png|jpeg/;
	return fileName.match(pattern);
}
function getFileInfo(fullName){
	var fileName, imgsrc, getLink, fileLink;
	if(checkImageType(fullName)){//이미지 파일일 경우
		imgsrc="/spring02/upload/displayFile?fileName="
				+fullName;
		fileLink=fullName.substr(14);
		var front=fullName.substr(0,12);
		var end=fullName.substr(14);
		getLink="/spring02/upload/displayFile?fileName="
				+front+end;
	}else{ //이미지가 아닌 경우
		fileLink=fullName.substr(12);
		getLink="/spring02/upload/displayFile?fileName="
				+fullName;
	}
	fileName=fileLink.substr(fileLink.indexOf("_")+1);
// {변수:값}	
	return {fileName:fileName, imgsrc:imgsrc,
		getLink:getLink, fullName:fullName};
}