package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.user.ChangeUserInfoRequest;
import com.brash.digital_bookshelf.dto.user.ChangeUserRefsRequest;
import com.brash.digital_bookshelf.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private/user")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("/change/info")
    public void changeUserInfo(@RequestBody ChangeUserInfoRequest request) {
        userUseCase.changeUserInfo(request);
    }

    @PostMapping("/change/refs")
    public void changeUserRefs(@RequestBody ChangeUserRefsRequest request) {
        userUseCase.changeUserRefs(request);
    }
}
