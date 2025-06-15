package com.roome.admin.roomeadminbe.domain.superadmin.controller;

import com.roome.admin.roomeadminbe.domain.admin.dto.response.AdminListResponse;
import com.roome.admin.roomeadminbe.domain.admin.dto.request.AdminListRequest;
import com.roome.admin.roomeadminbe.domain.superadmin.dto.request.InviteAdminRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.response.AdminResponse;
import com.roome.admin.roomeadminbe.domain.superadmin.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/super")
public class SuperAdminController {

	private final SuperAdminService superAdminService;

	@PostMapping("/invite")
	public ResponseEntity<Void> inviteAdmin(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Validated InviteAdminRequest inviteAdminRequestDto ) {
		superAdminService.inviteAdmin(inviteAdminRequestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/admins")
	public ResponseEntity<AdminListResponse> getAdminList(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute AdminListRequest adminListRequest) {
		AdminListResponse adminListResponse = superAdminService.getAdminList(adminListRequest);
		return ResponseEntity.ok().body(adminListResponse);
	}
}
