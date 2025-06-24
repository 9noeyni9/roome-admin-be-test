package com.roome.admin.roomeadminbe.global.security.jwt.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RefreshTokenService {

	private static final Duration REFRES_TOKEN_DURATION = Duration.ofDays(14);
	private final RedisTemplate<String, String> refreshTokenRedisTemplate;

	public RefreshTokenService(
			@Qualifier("refreshTokenRedisTemplate") RedisTemplate<String, String> refreshTokenRedisTemplate
	) {
		this.refreshTokenRedisTemplate = refreshTokenRedisTemplate;
	}

	public void saveRefreshToken(Long userId, String refreshToken) {
		refreshTokenRedisTemplate.opsForValue().set("refresh:" + userId, refreshToken, REFRES_TOKEN_DURATION);
	}

	public String getRefreshToken(Long userId) {
		return refreshTokenRedisTemplate.opsForValue().get("refresh:" + userId);
	}

	public void deleteRefreshToken(Long userId) {
		refreshTokenRedisTemplate.delete("refresh:" + userId);
	}

}

