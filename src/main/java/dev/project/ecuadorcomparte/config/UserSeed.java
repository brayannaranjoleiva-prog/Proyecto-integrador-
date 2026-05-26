package dev.project.ecuadorcomparte.config;

import dev.project.ecuadorcomparte.model.constant.RoleName;
import dev.project.ecuadorcomparte.model.entity.AppUser;
import dev.project.ecuadorcomparte.model.entity.Role;
import dev.project.ecuadorcomparte.model.repository.AppUserRepository;
import dev.project.ecuadorcomparte.model.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeed implements CommandLineRunner {

    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (!appUserRepository.existsByUsername("admin")) {

            Role adminRole = roleRepository.findByName(RoleName.ADMIN)
                    .orElseGet(() -> {

                        Role newRole = new Role();

                        newRole.setName(RoleName.ADMIN);

                        return roleRepository.save(newRole);
                    });

            AppUser admin = new AppUser();

            admin.setUsername("admin");

            admin.setEmail("admin@ecuadorcomparte.com");

            admin.setPassword(
                    passwordEncoder.encode("admin123")
            );

            admin.setEnabled(true);

            admin.setRole(RoleName.ADMIN);

            appUserRepository.save(admin);
        }


        if (!appUserRepository.existsByUsername("user")) {

            Role userRole = roleRepository.findByName(RoleName.USER)
                    .orElseGet(() -> {

                        Role newRole = new Role();

                        newRole.setName(RoleName.USER);

                        return roleRepository.save(newRole);
                    });

            AppUser user = new AppUser();

            user.setUsername("user");

            user.setEmail("garcia@gmail.com");

            user.setPassword(
                    passwordEncoder.encode("user123")
            );

            user.setEnabled(true);

            user.setRole(RoleName.USER);

            appUserRepository.save(user);
        }
    }
}