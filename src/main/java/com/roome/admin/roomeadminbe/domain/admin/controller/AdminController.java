package com.roome.admin.roomeadminbe.domain.admin.controller;

import static com.roome.admin.roomeadminbe.domain.common.dto.response.CommonResponse.ofDataWithHttpStatus;

import com.roome.admin.roomeadminbe.domain.common.dto.response.CommonResponse;
import com.roome.admin.roomeadminbe.domain.admin.dto.request.UpdateAdminInfoRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.request.UpdatePasswordRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.response.ReadAdminInfoResponse;
import com.roome.admin.roomeadminbe.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
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
	public ResponseEntity<CommonResponse<ReadAdminInfoResponse>> readInfo(@AuthenticationPrincipal UserDetails userDetails) {
		ReadAdminInfoResponse readAdminInfo = adminService.readInfo(userDetails.getUsername());
		return ofDataWithHttpStatus(readAdminInfo, HttpStatus.OK);
	}	

	@PatchMapping
	public ResponseEntity<CommonResponse<Void>> updateInfo(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Validated UpdateAdminInfoRequest updateAdminInfoRequest) {
		adminService.updateInfo(userDetails.getUsername(), updateAdminInfoRequest);
		return ofDataWithHttpStatus(null, HttpStatus.OK);
	}

	@PutMapping("/password")
	public ResponseEntity<CommonResponse<Void>> updatePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Validated UpdatePasswordRequest updatePasswordRequest) {
		adminService.updatePassword(userDetails.getUsername(), updatePasswordRequest);
		return ofDataWithHttpStatus(null, HttpStatus.OK);
	}
}
