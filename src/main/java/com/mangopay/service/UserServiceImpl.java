package com.mangopay.service;

import com.mangopay.dto.UserDTO;
import com.mangopay.mapper.Mapper;
import com.mangopay.model.User;
import com.mangopay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserServiceImpl(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> findAllUsers() {

        List<UserDTO> users = userRepository
                .findAll()
                .stream()
                .map(user -> mapper.toUserDTO(user))
                .collect(Collectors.toList());

        return users;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        return mapper.toUserDTO(userRepository.save(user));
    }
}
