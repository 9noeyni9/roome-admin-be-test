package com.roome.admin.roomeadminbe.domain.admin.dto.request;

import com.roome.admin.roomeadminbe.domain.admin.entity.AdminRole;
import com.roome.admin.roomeadminbe.domain.common.dto.request.ListRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class AdminListRequest extends ListRequest {

	private AdminRole role;
}
