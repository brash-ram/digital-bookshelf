package com.brash.digital_bookshelf.dto.user;

import com.brash.digital_bookshelf.data.enums.Gender;
import com.brash.digital_bookshelf.data.enums.ShowBirthType;

public record ChangeUserInfoRequest(
        String name,
        
        ShowBirthType showBirthType,
        
        Gender gender,
        
        String lifeStatus,
        
        String about
) {}
