package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.spring02.model.board.dao.ReplyDAO;
import com.example.spring02.model.board.dto.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	ReplyDAO replyDao;

	@Override
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) {
		List<ReplyVO> items = replyDao.list(bno, start, end);
		// 현재 사용자
	String userid = 
			(String) session.getAttribute("userid");
	for (ReplyVO vo : items) {
		if(vo.getSecret_reply().equals("y")) {
			if (userid == null){ //비로그인 상태
				vo.setReplytext("비밀댓글입니다.");
			}else{ //로그인 상태
				// 게시물 작성자
				String writer = vo.getWriter();
				// 댓글 작성자
				String replyer = vo.getReplyer();
				if (!userid.equals(writer) 
						&& !userid.equals(replyer)) {
					vo.setReplytext("비밀댓글입니다.");
				}
			}
		}
	}
		return items;
	}

	@Override
	public void create(ReplyVO vo) {
		replyDao.create(vo);
	}

	@Override
	public void update(ReplyVO vo) {
		replyDao.update(vo);
	}

	@Override
	public void delete(Integer rno) {
		replyDao.delete(rno); 
	}

	@Override
	public int count(int bno) {
		return replyDao.count(bno);
	}

	@Override
	public ReplyVO detail(int rno) {
		return replyDao.detail(rno);
	}

}
