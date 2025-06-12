package com.roome.admin.roomeadminbe.domain.admin.controller;

import com.roome.admin.roomeadminbe.domain.admin.dto.request.UpdateAdminInfoRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.request.UpdatePasswordRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.response.ReadAdminInfoResponse;
import com.roome.admin.roomeadminbe.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/info")
public class AdminController {

	private final AdminService adminService;

	@GetMapping
	public ResponseEntity<ReadAdminInfoResponse> readInfo(@AuthenticationPrincipal UserDetails userDetails) {
		ReadAdminInfoResponse readAdminInfo = adminService.readInfo(userDetails.getUsername());
		return ResponseEntity.ok().body(readAdminInfo);
	}

	@PatchMapping
	public ResponseEntity<Void> updateInfo(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Validated UpdateAdminInfoRequest updateAdminInfoRequest) {
		adminService.updateInfo(userDetails.getUsername(), updateAdminInfoRequest);
		return ResponseEntity.ok().build();
	}
}
