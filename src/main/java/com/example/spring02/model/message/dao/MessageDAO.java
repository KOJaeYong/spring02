package com.example.spring02.model.message.dao;

import com.example.spring02.model.message.dto.MessageVO;

//p.464
public interface MessageDAO {
	public void create(MessageVO vo);
	public MessageVO readMessage(int mid);
	public void updateState(int mid);
}
