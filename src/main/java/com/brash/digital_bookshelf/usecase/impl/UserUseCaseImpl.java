package com.brash.digital_bookshelf.usecase.impl;

import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.dto.user.ChangeUserInfoRequest;
import com.brash.digital_bookshelf.dto.user.ChangeUserRefsRequest;
import com.brash.digital_bookshelf.s3storage.S3Client;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
import com.brash.digital_bookshelf.usecase.UserUseCase;
import com.brash.digital_bookshelf.utils.AuthUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final AuthUtils authUtils;

    private final UserRepository userRepository;

    private final S3Properties s3Properties;

    @Transactional
    @Override
    public void changeUserInfo(ChangeUserInfoRequest dto) {
        User user = authUtils.getUserEntity();

        user.setName(dto.name());
        user.setAbout(dto.about());
        user.setGender(dto.gender());
        user.setShowBirthType(dto.showBirthType());
        user.setLifeStatus(dto.lifeStatus());
        user.setBirth(dto.birth());

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void changeUserRefs(ChangeUserRefsRequest dto) {
        User user = authUtils.getUserEntity();

        user.setRefTg(dto.refTg());
        user.setRefVk(dto.refVk());
        user.setRefSite(dto.refSite());
        user.setRefEmail(dto.refEmail());

        userRepository.save(user);
    }
}
