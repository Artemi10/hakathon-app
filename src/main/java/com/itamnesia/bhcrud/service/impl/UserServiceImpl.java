package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.exception.EntityNotFoundException;
import com.itamnesia.bhcrud.model.user.User;
import com.itamnesia.bhcrud.repository.UserRepository;
import com.itamnesia.bhcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(EntityNotFoundException::userNotFoundException);
    }
}
