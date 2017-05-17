package com.example.spring02.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 인터셉터 : 요청 전후에 자동으로 처리(경유)되는 클래스
//  preHandle() =>  return true => write.do => postHandle()
//              =>  return false; 
public class AdminInterceptor 
	extends HandlerInterceptorAdapter{
	//요청 전에 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션 객체 생성
		HttpSession session=request.getSession();
		if(session.getAttribute("admin_userid")==null){
			response.sendRedirect(
request.getContextPath()
+"/member/login.do?message=nologin");
			return false; //요청을 더이상 실행하지 않음
		}else{
			return true; //요청을 실행함
		}
	}
	// 요청이 끝난 후에 체크
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
}





