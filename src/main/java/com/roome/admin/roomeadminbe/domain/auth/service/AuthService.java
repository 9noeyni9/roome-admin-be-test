package com.roome.admin.roomeadminbe.domain.auth.service;

import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.repository.AdminRepository;
import com.roome.admin.roomeadminbe.domain.auth.dto.TokenResponseDto;
import com.roome.admin.roomeadminbe.domain.auth.dto.request.LoginRequest;
import com.roome.admin.roomeadminbe.domain.auth.dto.request.SendTempPasswordRequest;
import com.roome.admin.roomeadminbe.global.mail.MailService;
import com.roome.admin.roomeadminbe.global.security.jwt.provider.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

	private final AdminRepository adminRepository;
	private final MailService mailService;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	public void sendTempPassword(SendTempPasswordRequest sendTempPasswordRequest) {
		Admin admin = adminRepository.findByAdminEmail(sendTempPasswordRequest.getAdminEmail())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자입니다."));

		// 1. 임시 비밀번호 생성
		String tempPassword = generateRandomPassword();

		// 2. 비밀번호 저장
		admin.updateTempPassword(passwordEncoder.encode(tempPassword));
		adminRepository.save(admin);

		// 3. 메일 전송 요청
		mailService.sendTempPasswordEmail(admin.getAdminEmail(), tempPassword);
	}

	// 랜덤 비밀번호 생성 로직
	private String generateRandomPassword() {
		int length = 10;
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int idx = random.nextInt(chars.length());
			sb.append(chars.charAt(idx));
		}
		return sb.toString();
	}

}
