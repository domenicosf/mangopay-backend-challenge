package com.mangopay.service;

import com.mangopay.dto.UserDTO;
import com.mangopay.model.User;
import com.mangopay.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        UserDTO userDTO = new UserDTO();

        when(userRepository.save(any())).thenReturn(new User());

        UserDTO registeredUser = userService.registerUser(userDTO);

        assertEquals(userDTO, registeredUser);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testListIdeas() {
        List<UserDTO> users = Arrays.asList(new UserDTO(), new UserDTO());

        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));

        List<UserDTO> listUsers = userService.findAllUsers();

        assertEquals(users.size(), listUsers.size());
        verify(userRepository, times(1)).findAll();
    }
}