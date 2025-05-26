package com.roome.admin.roomeadminbe.domain.superadmin.dto.request;

import com.roome.admin.roomeadminbe.domain.admin.entity.AdminRole;
import com.roome.admin.roomeadminbe.domain.admin.entity.Status;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
public class InviteAdminRequestDto {

	private AdminRole adminRole;
	private String adminName;
	private String adminEmail;
}
