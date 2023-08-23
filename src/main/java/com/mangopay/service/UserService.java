package com.mangopay.service;

import com.mangopay.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUsers();

    UserDTO registerUser(UserDTO userDTO);
}
