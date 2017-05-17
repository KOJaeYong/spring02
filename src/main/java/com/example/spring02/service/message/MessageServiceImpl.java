package com.example.spring02.service.message;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.message.dao.MessageDAO;
import com.example.spring02.model.message.dao.PointDAO;
import com.example.spring02.model.message.dto.MessageVO;

@Service //현재 클래스를 스프링에서 관리하는 bean으로
public class MessageServiceImpl implements MessageService {
	@Inject
	MessageDAO messageDao;
	@Inject
	PointDAO pointDao;

// 트랜잭션 처리 대상 method	
	@Transactional
	@Override
	public void addMessage(MessageVO vo) {
		//로그 확인(공통업무)
		//핵심업무
		//메시지를 테이블에 저장
		messageDao.create(vo);
		//메시지를 발송한 회원에게 10포인트 추가
		pointDao.updatePoint(vo.getSender(), 10); 
	}

	@Override
	public MessageVO readMessage(String userid, int mid) {
		return null;
	}

}
