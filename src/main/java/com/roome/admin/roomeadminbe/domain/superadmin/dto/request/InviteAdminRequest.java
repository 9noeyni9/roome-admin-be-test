package com.roome.admin.roomeadminbe.domain.superadmin.dto.request;

import com.roome.admin.roomeadminbe.domain.admin.entity.AdminRole;
import lombok.Getter;

@Getter
public class InviteAdminRequest {

	private AdminRole adminRole;
	private String adminName;
	private String adminEmail;
	private String phoneNumber;
}
