package com.roome.admin.roomeadminbe.global.security.jwt.controller;

import com.roome.admin.roomeadminbe.global.security.jwt.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

	private final TokenService tokenService;

	@PostMapping("/refresh")
	public ResponseEntity<Void> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
		tokenService.refreshAccessToken(request, response);
		return ResponseEntity.ok().build();
	}
}
