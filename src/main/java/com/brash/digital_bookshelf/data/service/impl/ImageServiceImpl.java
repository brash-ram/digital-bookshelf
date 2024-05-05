package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.Image;
import com.brash.digital_bookshelf.data.repository.ImageRepository;
import com.brash.digital_bookshelf.data.service.ImageService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;


    @Override
    public Image getById(long id) {
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Image with id: %s -- is not found", id))
                );
    }
}
