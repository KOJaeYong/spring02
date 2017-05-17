package com.example.spring02.model.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.member.dto.MemberVO;
//현재 클래스를 스프링에서 관리하는 dao bean으로 등록
@Repository
public class MemberDAOImpl implements MemberDAO {
	//SqlSession 객체를 스프링에서 생성하여 주입시킴
	//의존관계 주입(Dependency Injection), 느슨한 결합
	@Inject
	SqlSession sqlSession; //mybatis 실행 객체

	@Override
	public boolean loginCheck(MemberVO vo) {
		String name
=sqlSession.selectOne("member.login_check", vo);
		return (name==null) ? false : true; 
	}

	@Override
	public MemberVO viewMember(MemberVO vo) {
	    return 
	      sqlSession.selectOne("member.viewMember",vo);
	}

}


