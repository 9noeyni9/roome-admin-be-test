package com.roome.admin.roomeadminbe.domain.admin.repository;

import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>, AdminRepositoryCustom {
	Optional<Admin> findByAdminEmail(String adminEmail);
}
