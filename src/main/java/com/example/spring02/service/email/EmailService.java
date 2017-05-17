package com.example.spring02.service.email;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring02.model.email.EmailVO;

@Service
public class EmailService {
	@Inject
	JavaMailSender mailSender; //메일 발송 객체
	public void sendMail(EmailVO vo) {
		try {
			MimeMessage msg=mailSender.createMimeMessage();
			//이메일 수신자
//import javax.mail.internet.MimeMessage.RecipientType;			
			msg.addRecipient(RecipientType.TO
					, new InternetAddress(vo.getReceiveMail()));
			//이메일 발신자
			msg.addFrom(new InternetAddress[] {
new InternetAddress(vo.getSenderMail(),vo.getSenderName()) 
			});
			//이메일 제목
			msg.setSubject(vo.getSubject(),"utf-8");
			//이메일 본문
			msg.setText(vo.getMessage(),"utf-8");
			mailSender.send(msg); //전송
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}











