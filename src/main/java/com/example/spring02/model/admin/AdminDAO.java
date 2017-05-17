package com.example.spring02.model.admin;

import com.example.spring02.model.member.dto.MemberVO;

public interface AdminDAO {
	public String loginCheck(MemberVO vo);
}
