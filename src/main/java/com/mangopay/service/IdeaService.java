package com.mangopay.service;

import com.mangopay.dto.IdeaDTO;

import java.util.List;

public interface IdeaService {
    IdeaDTO addIdea(IdeaDTO ideaDTO, Long userId);

    List<IdeaDTO> listIdeas(Long userId);
}
