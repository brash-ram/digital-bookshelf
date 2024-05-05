package com.brash.digital_bookshelf.dto.user;

import com.brash.digital_bookshelf.data.enums.Gender;
import com.brash.digital_bookshelf.data.enums.ShowBirthType;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import lombok.Data;

@Data
public class UserInfo{
        Long id;
        
        ImageDTO profileImage;
        
        String name;
        
        ShowBirthType showBirthType;
        
        Gender gender;
        
        String lifeStatus;
        
        String about;
        
        String refVk;
        
        String refTg;
        
        String refSite;
        
        String refEmail;
}
