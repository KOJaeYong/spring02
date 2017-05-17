package com.example.spring02.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.board.dto.ReplyVO;
import com.example.spring02.service.board.Pager;
import com.example.spring02.service.board.ReplyService;

// http://localhost:8080/spring02/view?bno=1
//http://localhost:8080/spring02/view/1
//http://localhost:8080/spring02/view/2

//REST : REpresentational State Transfer
//  하나의 URI가 하나의 고유한 리소스를 대표하도록 
//  설계된 개념
// http://localhost:8080/spring02/reply/list?bno=7
// http://localhost:8080/spring02/reply/list/1
// http://localhost:8080/spring02/reply/list/7

//@RestController   // 스프링 4.0부터 지원 
// @Controller  @RestController
@RestController
@RequestMapping("/reply/*")
public class ReplyController {
	
	@Inject
	ReplyService replyService;
	
	@RequestMapping("insert.do")
	public void insert(@ModelAttribute ReplyVO vo
			, HttpSession session){
		String userid
			=(String)session.getAttribute("userid");
		vo.setReplyer(userid); 
		replyService.create(vo); 
	}
	
	@RequestMapping(value="/delete/{rno}"
			, method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(
			@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		try {
			replyService.delete(rno);
			entity=new ResponseEntity<>("success"
					, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage()
					, HttpStatus.BAD_REQUEST);			
		}
		return entity;
	}
	
	
	//p.379 참조
// ResponseEntity : 데이터+http status code	
// @ResponseBody : 객체를 json으로	
// @RequestBody : json을 객체로	
	@RequestMapping(
			value="insert_rest.do",
			method=RequestMethod.POST)
	public ResponseEntity<String> insert_rest(
@RequestBody ReplyVO vo	, HttpSession session){
		ResponseEntity<String> entity=null;
		try{
			String userid
			=(String)session.getAttribute("userid");
			//String userid="kim";
			vo.setReplyer(userid); 
			replyService.create(vo); 
			entity=new ResponseEntity<String>(
					"success",HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity=new ResponseEntity<String>(
				e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}	
// /reply/list/1 => 1번 게시물의 댓글 목록 리턴
// /reply/list/2 => 2번 게시물의 댓글 목록 리턴
// @PathVariable : url에 입력될 변수값 지정	
// http://localhost:9000/spring02/reply/list/1/1
//		1번 게시물의 1페이지	
// http://localhost:9000/spring02/reply/list/1/2
//		1번 게시물의 2페이지	
	
	@RequestMapping(value="/detail/{rno}"
			, method=RequestMethod.GET)
	public ModelAndView reply_detail(
			@PathVariable("rno") int rno,
			ModelAndView mav){
		ReplyVO vo= replyService.detail(rno);
		mav.setViewName("board/reply_detail");
		mav.addObject("vo",vo);
		return mav;
	}
	//p.382 참조
	@RequestMapping(value="/list/{bno}/{curPage}"
			, method=RequestMethod.GET)
	public ModelAndView reply_list(
		@PathVariable("bno") int bno,
		@PathVariable int curPage,
		ModelAndView mav,
		HttpSession session)	{
		int count=replyService.count(bno); //댓글 갯수
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		List<ReplyVO> list
			=replyService.list(bno,start,end,session);
		// 뷰의 이름 지정
//		WEB-INF/views/board/reply_list.jsp		
		mav.setViewName("board/reply_list");
		// 뷰에 전달할 데이터 저장
		mav.addObject("list", list);
		mav.addObject("pager", pager);
		// reply_list.jsp로 포워딩
		return mav;
	}
// list.do?bno=100	
	
	@RequestMapping("list.do")
	public ModelAndView list(@RequestParam int bno,
			@RequestParam(defaultValue="1") int curPage,
			ModelAndView mav,
			HttpSession session)	{
		int count=replyService.count(bno); //댓글 갯수
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		List<ReplyVO> list
			=replyService.list(bno,start,end,session);
		// 뷰의 이름 지정
//		WEB-INF/views/board/reply_list.jsp		
		mav.setViewName("board/reply_list");
		// 뷰에 전달할 데이터 저장
		mav.addObject("list", list);
		mav.addObject("pager", pager);
		// reply_list.jsp로 포워딩
		return mav;
	}
	
	@RequestMapping(value="/update/{rno}"
, method={ RequestMethod.PUT, RequestMethod.PATCH } )
	public ResponseEntity<String> update(
@PathVariable("rno") int rno,@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity=null;
		try {
			vo.setRno(rno);
			//서비스 호출
			replyService.update(vo); 
			entity = 
new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = 
new ResponseEntity<String>(e.getMessage()
			,HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
		
	
// @Controller : return => view(화면)
// @RestController : return => 데이터
// @ResponseBody : 리턴 데이터를 json으로 변환(생략가능)	
	@RequestMapping("list_json.do")
	public @ResponseBody List<ReplyVO> list_json(
			@RequestParam(defaultValue="1") int curPage,
			@RequestParam int bno,
			HttpSession session)	{
		int count=10; //댓글 갯수
		Pager pager=new Pager(count,curPage);
		int start=pager.getPageBegin();
		int end=pager.getPageEnd();
		List<ReplyVO> list
			=replyService.list(bno,start,end,session);
		return list;
	}
	
}













