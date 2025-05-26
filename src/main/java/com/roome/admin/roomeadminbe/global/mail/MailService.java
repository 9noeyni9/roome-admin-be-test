package com.roome.admin.roomeadminbe.global.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;

	public void sendInvitationEmail(String toEmail) {
		String subject = "관리자 초대 메일";
		String message = """
                안녕하세요, 새로운 관리자 계정을 생성하려면 아래 링크를 클릭해주세요:

                %s

                감사합니다.
                """.formatted("https://roome-be.io.kr/login");

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(toEmail);
		email.setSubject(subject);
		email.setText(message);

		mailSender.send(email);
	}
}
