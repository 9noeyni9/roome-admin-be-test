package com.roome.admin.roomeadminbe.domain.superadmin.service;

import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.repository.AdminRepository;
import com.roome.admin.roomeadminbe.domain.superadmin.dto.request.InviteAdminRequestDto;
import com.roome.admin.roomeadminbe.global.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperAdminService {

	private final MailService mailService;
	private final AdminRepository adminRepository;
	public void inviteAdmin(InviteAdminRequestDto inviteAdminRequestDto) {

		Admin newAdmin = Admin.builder()
				.adminRole(inviteAdminRequestDto.getAdminRole())
				.adminName(inviteAdminRequestDto.getAdminName())
				.adminEmail(inviteAdminRequestDto.getAdminEmail())
				.password(null)
				.deletedAt(null)
				.isdeletedAt(false)
				.lastLoginAt(null)
				.status(null)
				.build();
		adminRepository.save(newAdmin);
		mailService.sendInvitationEmail(inviteAdminRequestDto.getAdminEmail());
	}
}
