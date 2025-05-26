package com.roome.admin.roomeadminbe.domain.superadmin.controller;

import com.roome.admin.roomeadminbe.domain.superadmin.dto.request.InviteAdminRequestDto;
import com.roome.admin.roomeadminbe.domain.superadmin.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/super")
public class SuperAdminController {

	private final SuperAdminService superAdminService;

	@PostMapping
	public ResponseEntity<Void> inviteAdmin(@RequestBody @Validated InviteAdminRequestDto inviteAdminRequestDto ) {
		superAdminService.inviteAdmin(inviteAdminRequestDto);
		return ResponseEntity.ok().build();
	}
}
