package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.Genre;
import com.brash.digital_bookshelf.data.entity.Tag;
import com.brash.digital_bookshelf.data.repository.TagRepository;
import com.brash.digital_bookshelf.data.service.TagService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Tag> getAllByNames(List<String> names) {
        List<Tag> tags = new ArrayList<>();
        for (String name : names) {
            if (tagRepository.existsByName(name)) {
                tags.add(
                        tagRepository.findByName(name).orElseThrow(() ->
                                new ResourceNotFoundException(
                                        String.format("Tag with name: %s -- is not found", name)
                                )
                        )
                );
            } else {
                tags.add(tagRepository.save(new Tag().setName(name)));
            }
        }
        return tags;
    }
}
