package com.example.spring02.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.member.dto.MemberVO;
import com.example.spring02.service.admin.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Inject
	AdminService adminService;
	// 관리자 로그인 폼으로 이동
	@RequestMapping("login.do")
	public String login(){
		return "admin/login";
	}
	// 로그인 체크 로직
	@RequestMapping("login_check.do") 
	public ModelAndView login_check(
MemberVO vo, HttpSession session, ModelAndView mav){
		String name=adminService.loginCheck(vo);
		if(name!=null){ //로그인 성공
//관리자 세션 변수 추가			
session.setAttribute("admin_userid", vo.getUserid());
session.setAttribute("admin_username", name);
session.setAttribute("userid", vo.getUserid());
session.setAttribute("username", name);
			// views/admin/admin.jsp로 이동
			mav.setViewName("admin/admin");
			mav.addObject("message","success");
		}else{ //로그인 실패
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session){
		//세션 정보를 초기화시킴(모든 세션변수 삭제)
		session.invalidate();
		return "redirect:/admin/login.do";
	}
}








