package com.brash.digital_bookshelf.usecase;

import com.brash.digital_bookshelf.dto.auth.AuthResponse;

public interface AuthUseCase {

    AuthResponse refresh(String refreshToken);

    AuthResponse signIn(String username, String password);

    AuthResponse signUp(String username, String password);

    void createAdmin(String username, String password);}
