package com.roome.admin.roomeadminbe.domain.admin.service;

import com.roome.admin.roomeadminbe.domain.admin.dto.request.UpdateAdminInfoRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.request.UpdatePasswordRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.response.ReadAdminInfoResponse;
import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;

	public ReadAdminInfoResponse readInfo(String adminEmail) {
		Admin admin = adminRepository.findByAdminEmail(adminEmail).orElseThrow();
		return ReadAdminInfoResponse.builder()
				.adminEmail(admin.getAdminEmail())
				.username(admin.getAdminName())
				.phoneNumber(admin.getPhoneNumber())
				.build();
	}
}
