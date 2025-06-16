package com.roome.admin.roomeadminbe.global.init;

import com.roome.admin.roomeadminbe.domain.admin.entity.ActivationStatus;
import com.roome.admin.roomeadminbe.domain.admin.entity.Admin;
import com.roome.admin.roomeadminbe.domain.admin.entity.AdminRole;
import com.roome.admin.roomeadminbe.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String email = "super@admin.com";

        boolean exists = adminRepository.existsByAdminEmail(email);

        if (!exists) {
            Admin superAdmin = Admin.builder()
                    .adminEmail(email)
                    .adminName("최고 관리자")
                    .password(passwordEncoder.encode("superAdmin1!"))
                    .adminRole(AdminRole.SUPER_ADMIN)
                    .activationStatus(ActivationStatus.ACTIVE)
                    .build();

            adminRepository.save(superAdmin);
            System.out.println("슈퍼 어드민 등록 완료");
        }
    }
}
