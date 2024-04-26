package com.brash.digital_bookshelf.usecase.impl;

import com.brash.digital_bookshelf.data.entity.AuthorityRole;
import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.repository.AuthorityRoleRepository;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.dto.auth.AuthResponse;
import com.brash.digital_bookshelf.exception.AccessDeniedException;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import com.brash.digital_bookshelf.security.jwt.JwtTokenProvider;
import com.brash.digital_bookshelf.usecase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final UserRepository userRepository;

    private final AuthorityRoleRepository authorityRoleRepository;

    private final PasswordEncoder passwordEncoder;

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
        if (!passwordEncoder.matches(username, user.getPassword())) {
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
                .setRoles(getDefaultRoles());
        user = userRepository.save(user);
        setAuthentication(user);
        return new AuthResponse(
                jwtTokenProvider.createAccessToken(user.getId()),
                jwtTokenProvider.createRefreshToken(user.getId())
        );
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
}
