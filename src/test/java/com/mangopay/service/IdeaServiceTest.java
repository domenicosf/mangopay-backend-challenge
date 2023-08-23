package com.mangopay.service;

import com.mangopay.dto.IdeaDTO;
import com.mangopay.model.Idea;
import com.mangopay.model.User;
import com.mangopay.repository.IdeaRepository;
import com.mangopay.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class IdeaServiceTest {
    @Autowired
    private IdeaService ideaService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private IdeaRepository ideaRepository;

    @Test
    public void testAddIdea() {
        IdeaDTO ideaDTO = new IdeaDTO();

        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        when(ideaRepository.save(any())).thenReturn(new Idea());

        IdeaDTO addedIdea = ideaService.addIdea(ideaDTO, 1L);

        assertEquals(ideaDTO, addedIdea);
        verify(ideaRepository, times(1)).save(any());
    }

    @Test
    public void testListIdeas() {
        List<IdeaDTO> ideas = Arrays.asList(new IdeaDTO(), new IdeaDTO());

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        when(ideaRepository.findByUserOrderByDayDesc(any())).thenReturn(Arrays.asList(new Idea(), new Idea()));

        List<IdeaDTO> fetchedIdeas = ideaService.listIdeas(1L);

        assertEquals(ideas.size(), fetchedIdeas.size());
        verify(userRepository, times(1)).findById(anyLong());
    }
}