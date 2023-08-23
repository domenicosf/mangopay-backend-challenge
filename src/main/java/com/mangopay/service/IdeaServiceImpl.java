package com.mangopay.service;

import com.mangopay.dto.IdeaDTO;
import com.mangopay.mapper.Mapper;
import com.mangopay.model.Idea;
import com.mangopay.model.User;
import com.mangopay.repository.IdeaRepository;
import com.mangopay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final UserRepository userRepository;
    private final IdeaRepository ideaRepository;
    private final Mapper mapper;

    public IdeaServiceImpl(UserRepository userRepository, IdeaRepository ideaRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.ideaRepository = ideaRepository;
        this.mapper = mapper;
    }

    @Override
    public IdeaDTO addIdea(IdeaDTO ideaDTO, Long userId) {
        Idea idea = mapper.toIdea(ideaDTO);
        Optional<User> user = userRepository.findById(userId);
        idea.setUser(user.get());

        return mapper.toIdeaDTO(ideaRepository.save(idea));
    }

    @Override
    public List<IdeaDTO> listIdeas(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return ideaRepository
                .findByUserOrderByDayDesc(user.get())
                .stream()
                .map(idea -> mapper.toIdeaDTO(idea))
                .collect(Collectors.toList());
    }
}
