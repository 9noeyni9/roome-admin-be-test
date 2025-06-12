package com.roome.admin.roomeadminbe.global.security.model;

import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.entity.AdminRole;
import com.roome.admin.roomeadminbe.domain.admin.entity.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AdminDetails implements UserDetails {

	private final Admin admin;

	public AdminDetails(Admin admin) {
		this.admin = admin;
	}

	public Long getAdminId() {
		return admin.getAdminId();
	}

	public AdminRole getAdminRole() {
		return admin.getAdminRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + admin.getAdminRole().name()));
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		return admin.getAdminEmail(); // 이메일을 ID로 쓴다고 가정
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return admin.getStatus() != Status.DORMANT; // 예시
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return Boolean.TRUE.equals(admin.isActivated());
	}
}
