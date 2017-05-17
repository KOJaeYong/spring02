package com.example.spring02.service.memo;

import java.util.List;

import com.example.spring02.model.memo.dto.MemoVO;

public interface MemoService {
	public List<MemoVO> list();
	public void insert(MemoVO vo);
	public void insert(String writer, String memo);
	public MemoVO memo_view(int idx);
	public void update(MemoVO vo);
	public void delete(int idx);
}
