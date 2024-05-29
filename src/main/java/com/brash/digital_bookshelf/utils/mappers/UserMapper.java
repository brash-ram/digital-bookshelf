package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    private final ImageMapper imageMapper;

    private final BookMapper bookMapper;

    private final UserService userService;

    public UserInfo toInfoDto(User user) {
        user = userService.getById(user.getId());
        UserInfo userInfo = modelMapper.map(user, UserInfo.class);
        userInfo.setProfileImage(imageMapper.toDto(user.getProfileImage()));
        userInfo.setRoles(user.getEnumRoles());
        userInfo.setLibrary(bookMapper.toListItems(new ArrayList<>(user.getLibrary())));
        if (user.getAuthorInfo() != null) {
            userInfo.setAuthorInfoId(user.getAuthorInfo().getId());
        }
        return userInfo;
    }
}
