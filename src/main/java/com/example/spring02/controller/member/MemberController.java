package com.example.spring02.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberVO;
import com.example.spring02.service.board.BoardService;
import com.example.spring02.service.member.MemberService;
//현재 클래스를 스프링에서 관리하는 컨트롤러 bean으로 생성
@Controller    
@RequestMapping("/member/*") 
public class MemberController {
	//로깅을 위한 변수
	private static final Logger logger
		=LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService memberService;
	
	@RequestMapping("login.do")
	public String login(){
		// views/member/login.jsp로 이동
		return "member/login";
	}
	@RequestMapping("login_check.do")
	public ModelAndView login_check(
			@ModelAttribute MemberVO vo
			, HttpSession session){
		boolean result=memberService.loginCheck(vo,session);
		ModelAndView mav=new ModelAndView();
		if( result == true ) { //로그인 성공
			//home.jsp로 이동
			mav.setViewName("home");
			mav.addObject("message","success"); 
		}else{ //로그인 실패
			//login.jsp로 이동
			mav.setViewName("member/login");
			mav.addObject("message","error"); 
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session
			,ModelAndView mav){
		memberService.logout(session);
		mav.setViewName("member/login");
		mav.addObject("message","logout");
		return mav;
	}
}








