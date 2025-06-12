package com.roome.admin.roomeadminbe.domain.auth.controller;

import com.roome.admin.roomeadminbe.domain.auth.dto.TokenResponseDto;
import com.roome.admin.roomeadminbe.domain.auth.dto.request.LoginRequest;
import com.roome.admin.roomeadminbe.domain.auth.dto.request.SendTempPasswordRequest;
import com.roome.admin.roomeadminbe.domain.auth.service.AuthService;
import com.roome.admin.roomeadminbe.global.security.jwt.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roome/bo/admin/common/auth")
public class AuthController {

	private final AuthService authService;

	// 임시 password 발급 (첫 로그인)
	@PostMapping("/password")
	public ResponseEntity<Void> sendTempPassword(@RequestBody SendTempPasswordRequest sendTempPasswordRequest) {
		authService.sendTempPassword(sendTempPasswordRequest);
		return ResponseEntity.ok().build();
	}
}
