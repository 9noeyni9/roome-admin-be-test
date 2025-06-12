package com.roome.admin.roomeadminbe.domain.superadmin.controller;

import com.roome.admin.roomeadminbe.domain.superadmin.dto.request.InviteAdminRequest;
import com.roome.admin.roomeadminbe.domain.superadmin.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/super")
public class SuperAdminController {

	private final SuperAdminService superAdminService;

	@PostMapping("/invite")
	public ResponseEntity<Void> inviteAdmin(@RequestBody @Validated InviteAdminRequest inviteAdminRequestDto ) {
		superAdminService.inviteAdmin(inviteAdminRequestDto);
		return ResponseEntity.ok().build();
	}
}
