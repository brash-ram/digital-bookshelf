package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.Tag;
import com.brash.digital_bookshelf.data.repository.TagRepository;
import com.brash.digital_bookshelf.data.service.TagService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag getById(long id) {
        return tagRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format("Tag with id: %s -- is not found", id)
                        )
                );
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
