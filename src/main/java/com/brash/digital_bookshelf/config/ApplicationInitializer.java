package com.brash.digital_bookshelf.config;

import com.brash.digital_bookshelf.data.entity.AuthorityRole;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.repository.AuthorityRoleRepository;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.s3storage.S3Client;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
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
    private final UserRepository userRepository;

    private final AuthUseCase authUseCase;

    private final S3Client minioClient;

    private final S3Properties s3Properties;

    @SneakyThrows
    @Override
    public void run(String... args) {
        insertAuthorityRoles();
        initBuckets();
        setAdminUser();
    }

    void initBuckets() {
        minioClient.createBucketIfNotExist(s3Properties.getBookBucket());
        minioClient.createBucketIfNotExist(s3Properties.getBookImageBucket());
        minioClient.createBucketIfNotExist(s3Properties.getProfileImageBucket());
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
        String username = "1@r.r";
        if (!userRepository.existsByUsername(username)) {
            authUseCase.createAdmin(username, "1");
        }
    }
}
