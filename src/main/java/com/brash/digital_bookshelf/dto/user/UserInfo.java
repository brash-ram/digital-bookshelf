package com.brash.digital_bookshelf.dto.user;

import com.brash.digital_bookshelf.data.enums.Gender;
import com.brash.digital_bookshelf.data.enums.Role;
import com.brash.digital_bookshelf.data.enums.ShowBirthType;
import com.brash.digital_bookshelf.dto.book.BookListItem;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo{
        private Long id;
        private ImageDTO profileImage;
        private String name;
        private ShowBirthType showBirthType;
        private Gender gender;
        private String lifeStatus;
        private String about;
        private String refVk;
        private String refTg;
        private String refSite;
        private String refEmail;
        private Date birth;
        private List<Role> roles;
        @Nullable
        private Long authorInfoId;
        private List<BookListItem> library;

}
