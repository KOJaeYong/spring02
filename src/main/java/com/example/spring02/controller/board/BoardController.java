package com.example.spring02.controller.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.BoardVO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.board.Pager;
import com.example.spring02.service.board.ReplyService;

@Controller   //현재 클래스를 컨트롤러 빈(bean)으로 등록
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger=
			LoggerFactory.getLogger(BoardController.class);
	
	//의존관계 주입 => BoardServiceImpl 생성
	// IoC 의존관계 역전
	@Inject 
	BoardService boardService;
	@Inject
	ReplyService replyService;
	
	@RequestMapping("imageUpload.do")
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam MultipartFile upload)
				throws Exception {
		//http header 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset-utf-8");
		//http body
		OutputStream out=null; //java.io
		PrintWriter printWriter=null; //java.io
		//업로드한 파일 이름
		String fileName=upload.getOriginalFilename();
		//바이트 배열로 변환
		byte[] bytes=upload.getBytes();
		//이미지를 업로드할 디렉토리(배포 경로로 설정)
		String uploadPath = 
"D:\\work_spring\\.metadata\\.plugins\\"
+ "org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"
+ "spring02\\WEB-INF\\views\\images\\" + fileName;
		out=new FileOutputStream(new File(uploadPath)); //java.io
		//서버에 저장됨
		out.write(bytes);
		String callback=request.getParameter("CKEditorFuncNum");
		printWriter=response.getWriter();
		String fileUrl
			=request.getContextPath()+"/images/"+fileName; 
		printWriter.println(
"<script>window.parent.CKEDITOR.tools.callFunction("
+ callback+",'"+fileUrl+"','이미지가 업로드되었습니다.')"
+ "</script>");
		//스트림 닫기
		printWriter.flush();
	}
	
	
	//@RequestMapping("write.do")
	// value="url이름", method="전송방식"
	@RequestMapping(value="write.do"
			, method=RequestMethod.GET)
	public String write(){
		return "board/write"; //write.jsp로 이동
	}
	
	@RequestMapping(value="insert.do"
			, method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo
			, HttpSession session)
		throws Exception {
		//세션에 저장된 아이디를 조회
		String writer = 
				(String)session.getAttribute("userid");
		vo.setWriter(writer); 
		boardService.create(vo);
		return "redirect:/board/list.do";
	}
//	public String insert(HttpServletRequest request){
//		String title=request.getParameter("title");
//	}
//@RequestParam(defaultValue="title")
// 기본값 할당	
	
	@RequestMapping("list.do") // /board/list.do
	public ModelAndView list(
@RequestParam(defaultValue="1") int curPage,			
@RequestParam(defaultValue="all") 
	String search_option,
@RequestParam(defaultValue="") String keyword) throws Exception{
		
		logger.debug("search_option:"+search_option);
		logger.debug("keyword:"+keyword); 
		
		//레코드 갯수 계산
		int count=boardService.countArticle(
				search_option, keyword);
		//페이지 나누기 관련 처리
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		List<BoardVO> list
=boardService.listAll(start, end, search_option, keyword);
		// 모델과 뷰
		ModelAndView mav=new ModelAndView();
		mav.setViewName("board/list"); //뷰를 list.jsp로 설정
		Map<String,Object> map=
				new HashMap<String,Object>();
		map.put("list", list);
		map.put("count", count);
		map.put("search_option", search_option);
		map.put("keyword", keyword);
		map.put("pager", pager); 
		mav.addObject("map", map);//데이터 저장
		return mav; //list.jsp로 List가 전달됨
	}
// @RequestParam get/post 방식으로 전달된 변수 1개	
// @ModelAttribute  객체로 저장됨	
//	public ModelAndView view(HttpServletRequest request){
//		int bno=Integer.parseInt(
//				request.getParameter("bno"));
// HttpSession 세션 객체
	@RequestMapping(value="view.do"
			, method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno
			,@RequestParam int curPage
			,@RequestParam String search_option
			,@RequestParam String keyword
			,HttpSession session)
		throws Exception {
		//조회수 증가 처리
		boardService.increaseViewcnt(bno,session); 
		   
		//모델(데이터)+뷰(화면)를 함께 전달하는 객체
		ModelAndView mav=new ModelAndView();
		//뷰의 이름 설정
		mav.setViewName("board/view"); //board/view.jsp
		//뷰에 전달할 데이터
		mav.addObject("dto", boardService.read(bno));
		mav.addObject("count",replyService.count(bno)); //댓글 갯수)
		mav.addObject("curPage",curPage);
		mav.addObject("search_option", search_option);
		mav.addObject("keyword", keyword);
		logger.info("mav:"+mav);
		return mav;
	}
// 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo
//							로 전달됨	
	
	@RequestMapping("update.do")
	public String update(@ModelAttribute BoardVO vo)
		throws Exception {
		boardService.update(vo);
//포워드 방식		
//		model.addAttribute("list",boardService.listAll());
//		return "board/list";
//리디렉트 방식		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int bno)
			throws Exception {
		//삭제 처리
		boardService.delete(bno); 
		//페이지 이동
		return "redirect:/board/list.do";
	}
// http://localhost:8080/spring02/board/getAttach/3
// @PathVariable : parameter가 아닌 url에 포함되는 변수
// @RequestParam : parameter 변수	
	@RequestMapping("/getAttach/{bno}")
	@ResponseBody  //view가 아닌 데이터를 리턴
	public List<String> getAttach(
			@PathVariable("bno") int bno){
		return boardService.getAttach(bno); 
	}
}














