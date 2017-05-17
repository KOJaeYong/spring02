package com.example.spring02.service.message;

import com.example.spring02.model.message.dto.MessageVO;

public interface MessageService {
	public void addMessage(MessageVO vo);
	public MessageVO readMessage(String userid,int mid);
}
