package com.example.spring02.model.message.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.message.dto.MessageVO;
//현재 클래스를 스프링에서 관리하는 dao bean으로 등록
@Repository
public class MessageDAOImpl implements MessageDAO {
// mybatis의 SqlSession 객체를 스프링에서 생성하여 주입시킴
	@Inject
	SqlSession sqlSession;
	
	@Override
	public void create(MessageVO vo) {
		sqlSession.insert("message.create", vo);
	}

	@Override
	public MessageVO readMessage(int mid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(int mid) {
		// TODO Auto-generated method stub

	}

}
