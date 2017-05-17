package com.example.spring02.service.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring02.model.member.dao.MemberDAO;
import com.example.spring02.model.member.dao.MemberDAOImpl;
import com.example.spring02.model.member.dto.MemberVO;

//현재 클래스를 스프링에서 관리하는 서비스 bean으로 등록
@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO memberDao;
	
	@Override
	public boolean loginCheck(
			MemberVO vo,HttpSession session) {
		boolean result=memberDao.loginCheck(vo);
		if(result){ //true일 경우 세션 등록
		 MemberVO vo2=viewMember(vo);
		 //세션 변수 등록
  		 session.setAttribute("userid",vo2.getUserid());
  		 session.setAttribute(
  				 "username",vo2.getUsername());
		}
		return result; 
	}
	@Override
	public void logout(HttpSession session) {
		//세션변수 개별 삭제
		//session.removeAttribute("userid"); 
		//세션 정보를 초기화시킴
		session.invalidate();
	}
	@Override
	public MemberVO viewMember(MemberVO vo) {
		return memberDao.viewMember(vo);
	}
}





