package com.mangopay.mapper;

import com.mangopay.dto.IdeaDTO;
import com.mangopay.dto.UserDTO;
import com.mangopay.model.Idea;
import com.mangopay.model.User;
import org.mapstruct.Mapping;

@org.mapstruct.Mapper
public interface Mapper {
    @Mapping(source = "id", target = "id")
    IdeaDTO toIdeaDTO(Idea idea);

    @Mapping(target = "id", ignore = true)
    Idea toIdea(IdeaDTO ideaDTO);

    @Mapping(source = "id", target = "id")
    UserDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    User toUser(UserDTO userDTO);
}
