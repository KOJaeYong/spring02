package com.example.spring02.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.service.board.BoardService;
import com.example.spring02.util.MediaUtils;
import com.example.spring02.util.UploadFileUtils;

//p.522
@Controller
public class UploadController {
	private static final Logger logger=
	 LoggerFactory.getLogger(UploadController.class);
	@Inject
	BoardService boardService;
	
	// xml에 설정된 리소스 참조
	// bean의 id가 uploadPath인 태그를 참조
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/upload/uploadForm", 
			method=RequestMethod.GET)
	public void uploadForm(){
		// upload/uploadForm.jsp로 포워딩
	}
// 업로드 버튼 => 임시디렉토리에 업로드
// => 파일정보가 file에 저장	
//	=> 지정된 디렉토리에 저장 =>
	@RequestMapping(value="/upload/uploadForm", 
			method=RequestMethod.POST)
	public ModelAndView uploadForm(
			MultipartFile file, ModelAndView mav)
		throws Exception {
		logger.info("파일이름:"
			+file.getOriginalFilename());
		String savedName=file.getOriginalFilename();
		logger.info("파일크기:"+file.getSize());
		logger.info("컨텐트 타입:"+file.getContentType());
		savedName = 
				uploadFile(savedName, file.getBytes());
		mav.setViewName("upload/uploadResult");
		mav.addObject("savedName",savedName );
		return mav; //uploadResult.jsp로 포워딩
	}
	//p.528
//파일 이름이 중복되지 않도록 처리	
	private String uploadFile(String originalName
			, byte[] fileData) throws Exception {
// uuid 생성(Universal Unique IDentifier,범용 고유 식별자)		
		UUID uid=UUID.randomUUID();
		String savedName=uid.toString()+"_"+originalName;
		File target=new File(uploadPath, savedName);
		System.out.println("나의 확인 : " + uploadPath + " : "
				+ target);
		// 임시 디렉토리에 저장된 업로드된 파일을 
		// 지정된 디렉토리로 복사	
		// FileCopyUtils.copy( 바이트배열, 파일객체 )			
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
	@RequestMapping(value="/upload/uploadAjax"
			, method=RequestMethod.GET)
	public void uploadAjax(){
		// uploadAjax.jsp로 포워딩
	}
	//p.544
//produces="text/plan;charset=utf-8" : 파일의 한글처리	
	@ResponseBody //view가 아닌 데이터를 리턴
	@RequestMapping(value="/upload/uploadAjax"
		,method=RequestMethod.POST
		,produces="text/plan;charset=utf-8")
	public ResponseEntity<String> uploadAjax(
			MultipartFile file) throws Exception{
		logger.info(
"originalName:"+file.getOriginalFilename());
		logger.info("size:"+ file.getSize());
		logger.info(
				"contentType:"+ file.getContentType());
		
		return new ResponseEntity<String>(
			UploadFileUtils.uploadFile(uploadPath
				,file.getOriginalFilename()
				,file.getBytes())
				,HttpStatus.OK);
	}
// 이미지 표시 기능	
	@ResponseBody //view가 아닌 data 리턴
	@RequestMapping("/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(
			String fileName) throws Exception {
		//서버의 파일을 다운로드하기 위한 스트림
		InputStream in=null; //java.io
		ResponseEntity<byte[]> entity=null;
		try{
			//확장자 검사
			String formatName=fileName.substring(
					fileName.lastIndexOf(".")+1);
			MediaType mType=
MediaUtils.getMediaType(formatName);
			//헤더 구성 객체
			HttpHeaders headers=new HttpHeaders();
			// InputStream 생성
			in=new FileInputStream(uploadPath+fileName);
			if(mType != null){ //이미지 파일이면
				headers.setContentType(mType);
			}else{ //이미지가 아니면
fileName=fileName.substring(fileName.indexOf("_")+1);
//다운로드용 컨텐트 타입
headers.setContentType(
		MediaType.APPLICATION_OCTET_STREAM);
// 큰 따옴표 내부에  "  \"   "
//바이트배열을 스트링으로
//iso-8859-1 서유럽언어
//new String(fileName.getBytes("utf-8"),"iso-8859-1")
				headers.add("Content-Disposition"
,"attachment; filename=\""
+new String(fileName.getBytes("utf-8"),"iso-8859-1")
	+"\"");
//				headers.add("Content-Disposition"
//,"attachment; filename='"+fileName+"'");
			}
			// 바이트배열, 헤더
			entity=new ResponseEntity<byte[]>(
IOUtils.toByteArray(in),headers,HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(
HttpStatus.BAD_REQUEST);
		}finally{
			in.close(); //스트림 닫기
		}
		return entity;
	}
	
	@ResponseBody // view가 아닌 데이터 리턴
	@RequestMapping(value="/upload/deleteFile"
		, method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(
			String fileName){
		//확장자
		String formatName=fileName.substring(
				fileName.lastIndexOf(".")+1);
		//이미지 여부 검사
		MediaType mType
			=MediaUtils.getMediaType(formatName);
		//이미지의 경우(썸네일 + 원본파일 삭제)
		//이미지가 아니면 원본파일만 삭제
		if(mType != null){ //이미지 파일이면
			String front=fileName.substring(0, 12);
			String end=fileName.substring(14);
			//썸네일 삭제
			new File(uploadPath+(front+end).replace(
				'/',File.separatorChar)).delete();
		}
		//원본 파일 삭제
		new File(uploadPath+fileName.replace(
			'/',File.separatorChar)).delete();
		//레코드 삭제
		boardService.deleteFile(fileName); 
		// 데이터와 http 상태 코드 전송
		return new ResponseEntity<String>("deleted"
				,HttpStatus.OK);
	}
}













