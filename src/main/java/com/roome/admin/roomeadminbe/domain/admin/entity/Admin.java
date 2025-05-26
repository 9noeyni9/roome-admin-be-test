package com.roome.admin.roomeadminbe.domain.admin.entity;

import com.roome.admin.roomeadminbe.domain.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admins")
public class Admin extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;
	private AdminRole adminRole;
	private String adminName;
	private String adminEmail;
	private String password;
	private LocalDateTime deletedAt;
	private Boolean isdeletedAt;
	private Status status;
	private LocalDateTime lastLoginAt;
}
