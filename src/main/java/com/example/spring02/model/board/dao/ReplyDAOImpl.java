package com.example.spring02.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.board.dto.ReplyVO;


@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> list(
			Integer bno,int start, int end) {
		Map<String,Object> map=
				new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("bno", bno);
		return 
	sqlSession.selectList("reply.listReply", map);
	}

	@Override
	public void create(ReplyVO vo) {
		sqlSession.insert("reply.insertReply", vo); 
	}

	@Override
	public void update(ReplyVO vo) {
		sqlSession.update("reply.update", vo); 
	}

	@Override
	public void delete(Integer rno) {
		sqlSession.delete("reply.delete", rno);
	}

	@Override
	public int count(int bno) {
		return sqlSession.selectOne("reply.count", bno);
	}

	@Override
	public ReplyVO detail(int rno) {
		return sqlSession.selectOne("reply.detail",rno);
	}

}








