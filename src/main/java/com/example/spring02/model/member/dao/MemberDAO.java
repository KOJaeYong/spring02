package com.example.spring02.model.member.dao;

import com.example.spring02.model.member.dto.MemberVO;

public interface MemberDAO {
	public boolean loginCheck(MemberVO vo);
	public MemberVO viewMember(MemberVO vo);
}
