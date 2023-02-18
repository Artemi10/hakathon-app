package com.itamnesia.bhcrud.service;

import com.itamnesia.bhcrud.model.user.User;

public interface UserService {

    User saveUser(User user);

    User getUserByPhoneNumber(String phoneNumber);
}
