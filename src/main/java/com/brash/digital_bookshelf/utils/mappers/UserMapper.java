package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    private final ImageMapper imageMapper;

    public UserInfo toInfoDto(User user) {
        UserInfo userInfo = modelMapper.map(user, UserInfo.class);
        userInfo.setProfileImage(imageMapper.toDto(user.getProfileImage()));
        return userInfo;
    }
}
