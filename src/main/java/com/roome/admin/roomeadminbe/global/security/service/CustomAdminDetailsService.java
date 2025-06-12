package com.roome.admin.roomeadminbe.global.security.service;

import com.roome.admin.roomeadminbe.domain.admin.entity.ActivationStatus;
import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.repository.AdminRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomAdminDetailsService implements UserDetailsService {

	private final AdminRepository adminRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String adminEmail) throws UsernameNotFoundException {
		return adminRepository.findByAdminEmail(adminEmail)
				.map(this::createUser)
				.orElseThrow(() -> new UsernameNotFoundException("관리자 이메일을 찾을 수 없습니다: " + adminEmail));
	}

	private org.springframework.security.core.userdetails.User createUser(Admin admin) {
		if (admin.getActivationStatus() != ActivationStatus.ACTIVE) {
			throw new RuntimeException(admin.getAdminEmail() + " -> 활성화되어 있지 않습니다.");
		}

		List<GrantedAuthority> grantedAuthorities = List.of(
				new SimpleGrantedAuthority(admin.getAdminRole().getRoleName())
		);

		return new org.springframework.security.core.userdetails.User(
				admin.getAdminEmail(),
				admin.getPassword(),
				grantedAuthorities
		);
	}
}
