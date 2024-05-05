package com.brash.digital_bookshelf.utils.mappers;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.dto.image.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageMapper {

    public ImageDTO toDto(Image image) {
        return new ImageDTO(image.getId(), image.getBlurhash());
    }

}
