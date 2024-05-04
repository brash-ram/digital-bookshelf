package com.brash.digital_bookshelf.controller;


import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.auth.AuthResponse;
import com.brash.digital_bookshelf.dto.auth.JwtRefreshRequest;
import com.brash.digital_bookshelf.dto.auth.SignInRequest;
import com.brash.digital_bookshelf.usecase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/signIn")
    public ResponseEntity<BasicApiResponse<AuthResponse>> signIn(@RequestBody SignInRequest authRequest) {
        return ResponseEntity.ok(new BasicApiResponse<>(authUseCase.signIn(authRequest.username(), authRequest.password())));
    }


    @PostMapping("/signUp")
    public ResponseEntity<BasicApiResponse<AuthResponse>> signUp(@RequestBody SignInRequest authRequest) {
        return ResponseEntity.ok(new BasicApiResponse<>(authUseCase.signUp(authRequest.username(), authRequest.password())));
    }

    @PostMapping("/refresh")
    public ResponseEntity<BasicApiResponse<AuthResponse>> refresh(@RequestBody JwtRefreshRequest refreshRequest) {
        return ResponseEntity.ok(new BasicApiResponse<>(authUseCase.refresh(refreshRequest.refreshToken())));
    }
}
