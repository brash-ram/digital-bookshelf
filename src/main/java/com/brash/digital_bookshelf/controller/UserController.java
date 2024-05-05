package com.brash.digital_bookshelf.controller;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.dto.BasicApiResponse;
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

    private final ImageUseCase imageUseCase;

    private final S3Properties s3Properties;

    private final AuthUtils authUtils;

    private final UserMapper userMapper;

    @PostMapping("/change/info")
    public void changeUserInfo(@RequestBody ChangeUserInfoRequest request) {
        userUseCase.changeUserInfo(request);
    }

    @PostMapping("/change/refs")
    public void changeUserRefs(@RequestBody ChangeUserRefsRequest request) {
        userUseCase.changeUserRefs(request);
    }

    @GetMapping("/me")
    public ResponseEntity<BasicApiResponse<UserInfo>> me() {
        return ResponseEntity.ok(new BasicApiResponse<>(userMapper.toInfoDto(authUtils.getUserEntity())));
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable("id") long id) {
        S3File image = imageUseCase.getImage(id, s3Properties.getProfileImageBucket());
        String contentType = URLConnection.guessContentTypeFromName("." + image.getFilename().split(".")[1]);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image.getContent());
    }
}
