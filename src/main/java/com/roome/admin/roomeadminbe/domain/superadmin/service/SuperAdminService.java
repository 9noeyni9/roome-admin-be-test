package com.roome.admin.roomeadminbe.domain.superadmin.service;

import com.roome.admin.roomeadminbe.domain.admin.entity.ActivationStatus;
import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.repository.AdminRepository;
import com.roome.admin.roomeadminbe.domain.superadmin.dto.request.InviteAdminRequest;
import com.roome.admin.roomeadminbe.global.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

	private final MailService mailService;
	private final AdminRepository adminRepository;
	public void inviteAdmin(InviteAdminRequest inviteAdminRequestDto) {

		Admin newAdmin = Admin.builder()
				.adminRole(inviteAdminRequestDto.getAdminRole())
				.adminName(inviteAdminRequestDto.getAdminName())
				.adminEmail(inviteAdminRequestDto.getAdminEmail())
				.phoneNumber(inviteAdminRequestDto.getPhoneNumber())
				// 관리자 인증 전
				.activationStatus(ActivationStatus.PENDING)
				// 최초 가입 전 비밀번호 null
				.password(null)
				.deletedAt(null)
				.isDeletedAt(false)
				.lastLoginAt(null)
				.status(null)
				.build();
		adminRepository.save(newAdmin);
		mailService.sendInvitationEmail(inviteAdminRequestDto.getAdminEmail());
	}
}
