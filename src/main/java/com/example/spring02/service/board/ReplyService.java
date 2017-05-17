package com.example.spring02.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.spring02.model.board.dto.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> list(
Integer bno,int start, int end, HttpSession session );
	public int count(int bno);
	public void create(ReplyVO vo);
	public void update(ReplyVO vo);
	public void delete(Integer rno);
	//댓글의 상세정보
	public ReplyVO detail(int rno);
}
