package com.brash.digital_bookshelf.usecase;

import com.brash.digital_bookshelf.dto.user.ChangeUserInfoRequest;
import com.brash.digital_bookshelf.dto.user.ChangeUserRefsRequest;

public interface UserUseCase {
    void changeUserInfo(ChangeUserInfoRequest request);

    void changeUserRefs(ChangeUserRefsRequest request);
}
