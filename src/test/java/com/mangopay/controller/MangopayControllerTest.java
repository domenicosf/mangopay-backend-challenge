package com.mangopay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangopay.dto.IdeaDTO;
import com.mangopay.dto.UserDTO;
import com.mangopay.service.IdeaService;
import com.mangopay.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MangopayControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private IdeaService ideaService;

    @Test
    public void testRegisterUser() throws Exception {
        UserDTO userDTO = UserDTO
                .builder()
                .id(1L)
                .email("test@test.com")
                .name("test")
                .build();

        when(userService.registerUser(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").exists());

        verify(userService, times(1)).registerUser(any(UserDTO.class));
    }

    @Test
    public void testRegisterUserValidations() throws Exception {
        UserDTO userDTO = UserDTO
                .builder()
                .id(1L)
                .email("test.com")
                .name("")
                .build();

        mockMvc.perform(post("/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors", hasSize(2)));
    }

    @Test
    public void testListAllUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(new UserDTO(), new UserDTO());

        when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    public void testAddIdea() throws Exception {
        IdeaDTO ideaDTO = IdeaDTO
                .builder()
                .id(1L)
                .sourceUrl("test")
                .description("test")
                .day(LocalDate.now())
                .build();

        when(ideaService.addIdea(any(IdeaDTO.class), anyLong())).thenReturn(ideaDTO);

        mockMvc.perform(post("/v1/users/{userId}/ideas", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ideaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        verify(ideaService, times(1)).addIdea(any(IdeaDTO.class), anyLong());
    }

    @Test
    public void testAddIdeaValidations() throws Exception {
        IdeaDTO ideaDTO = IdeaDTO
                .builder()
                .id(1L)
                .sourceUrl("")
                .description("")
                .day(null)
                .build();

        mockMvc.perform(post("/v1/users/{userId}/ideas", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ideaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationErrors", hasSize(3)));
    }

    @Test
    public void testListIdeas() throws Exception {
        List<IdeaDTO> ideas = Arrays.asList(new IdeaDTO(), new IdeaDTO());

        when(ideaService.listIdeas(anyLong())).thenReturn(ideas);

        mockMvc.perform(get("/v1/users/{userId}/ideas", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(ideaService, times(1)).listIdeas(anyLong());
    }

}