package com.brash.digital_bookshelf.config;

import com.brash.digital_bookshelf.data.entity.AuthorityRole;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.repository.AuthorityRoleRepository;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.usecase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationInitializer implements CommandLineRunner {

    private final AuthorityRoleRepository authorityRoleRepository;

    private final AuthUseCase authUseCase;

    @SneakyThrows
    @Override
    public void run(String... args) {
        insertAuthorityRoles();
    }

    void insertAuthorityRoles() {
        List<AuthorityRole> roles = Arrays.stream(Role.values())
                .map(role -> new AuthorityRole().setName(role))
                .toList();

        for (var role : roles) {
            if (!authorityRoleRepository.existsByName(role.getName())) {
                authorityRoleRepository.save(role);
            }
        }
    }

    void setAdminUser() {
        authUseCase.signUp("admin", "admin");
    }
}
