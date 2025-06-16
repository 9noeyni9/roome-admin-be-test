package com.roome.admin.roomeadminbe.domain.superadmin.controller;

import com.roome.admin.roomeadminbe.domain.admin.dto.request.AdminListRequest;
import com.roome.admin.roomeadminbe.domain.admin.dto.response.AdminListResponse;
import com.roome.admin.roomeadminbe.domain.superadmin.dto.request.InviteAdminRequest;
import com.roome.admin.roomeadminbe.domain.superadmin.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/super")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/invite")
    public ResponseEntity<Void> inviteAdmin(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Validated InviteAdminRequest inviteAdminRequestDto) {
        superAdminService.inviteAdmin(inviteAdminRequestDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/admins")
    public ResponseEntity<AdminListResponse> getAdminList(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute AdminListRequest adminListRequest) {
        AdminListResponse adminListResponse = superAdminService.getAdminList(adminListRequest);
        return ResponseEntity.ok().body(adminListResponse);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PatchMapping("/admins/{adminId}/delete")
    public ResponseEntity<Void> deleteAdminRole(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("adminId") Long adminId) {
        superAdminService.deleteAdminRole(adminId);
        return ResponseEntity.ok().build();
    }
}
