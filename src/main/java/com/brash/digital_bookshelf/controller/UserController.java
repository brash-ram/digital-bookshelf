package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
import com.brash.digital_bookshelf.dto.EmptyApiResponse;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import com.brash.digital_bookshelf.dto.user.ChangeUserInfoRequest;
import com.brash.digital_bookshelf.dto.user.ChangeUserRefsRequest;
import com.brash.digital_bookshelf.dto.user.UserInfo;
import com.brash.digital_bookshelf.s3storage.S3File;
import com.brash.digital_bookshelf.s3storage.config.S3Properties;
import com.brash.digital_bookshelf.usecase.ImageUseCase;
import com.brash.digital_bookshelf.usecase.UserUseCase;
import com.brash.digital_bookshelf.utils.AuthUtils;
import com.brash.digital_bookshelf.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.net.URLConnection;

@RestController
@RequestMapping("/api/private/user")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    private final AuthUtils authUtils;

    private final UserMapper userMapper;

    @PostMapping("/change/info")
    public ResponseEntity<EmptyApiResponse> changeUserInfo(@RequestBody ChangeUserInfoRequest request) {
        userUseCase.changeUserInfo(request);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @PostMapping("/change/refs")
    public ResponseEntity<EmptyApiResponse> changeUserRefs(@RequestBody ChangeUserRefsRequest request) {
        userUseCase.changeUserRefs(request);
        return ResponseEntity.ok(new EmptyApiResponse());
    }

    @GetMapping("/me")
    public ResponseEntity<BasicApiResponse<UserInfo>> me() {
        return ResponseEntity.ok(new BasicApiResponse<>(userMapper.toInfoDto(authUtils.getUserEntity())));
    }

    @GetMapping("/change/author")
    public ResponseEntity<EmptyApiResponse> createAuthor() {
        userUseCase.createAuthor();
        return ResponseEntity.ok(new EmptyApiResponse());
    }
}
