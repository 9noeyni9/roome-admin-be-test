package com.roome.admin.roomeadminbe.domain.admin.entity;


public enum AdminRole {
	SUPER_ADMIN,
	BEFORE_OPERATION_MANAGER,
	OPERATION_MANAGER,
	SYSTEM_MANAGER,
	BEFORE_SYSTEM_MANAGER;

	public String getRoleName() {
		return "ROLE_" + this.name();
	}
}
