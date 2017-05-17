package com.example.spring02.controller.email;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring02.model.email.EmailVO;
import com.example.spring02.service.email.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {
	@Inject
	EmailService emailService;
	@RequestMapping("write.do")
	public String write(){
// views/email/write.jsp로 포워딩		
		return "/email/write";
	}
	@RequestMapping("send.do")
	public String send(@ModelAttribute EmailVO vo,Model model) {
		try {
			emailService.sendMail(vo);
			model.addAttribute("message","메일이 발송되었습니다");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","이메일 발송 실패...");
		}
		return "/email/write";
	}
}










