package com.brash.digital_bookshelf.usecase.impl;

import com.brash.digital_bookshelf.data.entity.AuthorInfo;
import com.brash.digital_bookshelf.data.entity.AuthorityRole;
import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.enums.Gender;
import com.brash.digital_bookshelf.data.enums.RecommendationMarks;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.enums.ShowBirthType;
import com.brash.digital_bookshelf.data.repository.AuthorInfoRepository;
import com.brash.digital_bookshelf.data.repository.AuthorityRoleRepository;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.dto.auth.AuthResponse;
import com.brash.digital_bookshelf.dto.recommendation.MarkRabbitDTO;
import com.brash.digital_bookshelf.dto.recommendation.UserRabbitDTO;
import com.brash.digital_bookshelf.exception.AccessDeniedException;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import com.brash.digital_bookshelf.security.jwt.JwtTokenProvider;
import com.brash.digital_bookshelf.service.RecommendationRabbitProducer;
import com.brash.digital_bookshelf.usecase.AuthUseCase;
import com.brash.digital_bookshelf.usecase.ImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserRepository userRepository;

    private final AuthorityRoleRepository authorityRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final ImageUseCase imageUseCase;

    private final AuthorInfoRepository authorInfoRepository;

    private final RecommendationRabbitProducer recommendation;

    private final Random random = new Random();

    @Override
    public AuthResponse refresh(String refreshToken) {
        if (!jwtTokenProvider.isValid(refreshToken)) {
            throw new AccessDeniedException();
        }
        Long userId = jwtTokenProvider.parseId(refreshToken);
        return new AuthResponse(jwtTokenProvider.createAccessToken(userId), jwtTokenProvider.createRefreshToken(userId));
    }

    @Override
    public AuthResponse signIn(String username, String password) {
        User user = userService.getByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("User with this password is not found");
        }
        setAuthentication(user);
        return new AuthResponse(
                jwtTokenProvider.createAccessToken(user.getId()),
                jwtTokenProvider.createRefreshToken(user.getId())
        );
    }

    @Override
    public AuthResponse signUp(String username, String password) {
        User user = new User()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(password))
                .setName(generateName())
                .setRoles(getDefaultRoles())
                .setGender(Gender.NOT_SHOW)
                .setShowBirthType(ShowBirthType.NOT_SHOW);

        Image profileImage = imageUseCase.getProfileImageFromRobohash(user);
        user.setProfileImage(profileImage);

        user = userRepository.save(user);
        setAuthentication(user);

        recommendation.sendUser(new UserRabbitDTO(
                user.getId()
        ));

        return new AuthResponse(
                jwtTokenProvider.createAccessToken(user.getId()),
                jwtTokenProvider.createRefreshToken(user.getId())
        );
    }

    @Transactional
    @Override
    public void createAdmin(String username, String password) {
        User user = new User()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(password))
                .setName(generateName())
                .setRoles(Set.of(
                        authorityRoleRepository.getAuthorityRoleByName(Role.ADMIN),
                        authorityRoleRepository.getAuthorityRoleByName(Role.USER),
                        authorityRoleRepository.getAuthorityRoleByName(Role.AUTHOR)
                ))
                .setGender(Gender.NOT_SHOW)
                .setShowBirthType(ShowBirthType.NOT_SHOW);

        AuthorInfo authorInfo = new AuthorInfo()
                .setUser(user);

        authorInfoRepository.save(authorInfo);

        Image profileImage = imageUseCase.getProfileImageFromRobohash(user);
        user.setProfileImage(profileImage);

        userRepository.save(user);

        recommendation.sendUser(new UserRabbitDTO(
                user.getId()
        ));
    }

    private void setAuthentication(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Set<AuthorityRole> getDefaultRoles() {
        return Set.of(
                authorityRoleRepository.getAuthorityRoleByName(Role.USER)
        );
    }

    private String generateName() {
        return "Пользователь " + random.nextInt(1000000, 9999999);
    }
}
