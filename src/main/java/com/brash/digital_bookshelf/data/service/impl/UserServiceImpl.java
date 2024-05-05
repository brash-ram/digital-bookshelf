package com.brash.digital_bookshelf.data.service.impl;

import com.brash.digital_bookshelf.data.entity.User;
import com.brash.digital_bookshelf.data.repository.UserRepository;
import com.brash.digital_bookshelf.data.service.UserService;
import com.brash.digital_bookshelf.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with username: %s -- is not found", username))
                );
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with id: %s -- is not found", id))
                );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
