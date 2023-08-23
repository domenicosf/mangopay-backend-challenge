package com.mangopay.controller;

import com.mangopay.dto.IdeaDTO;
import com.mangopay.dto.UserDTO;
import com.mangopay.service.IdeaService;
import com.mangopay.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@OpenAPIDefinition(info = @Info(title = "Users API", version = "v1"))
public class MangopayController {
    private final UserService userService;
    private final IdeaService ideaService;
    public MangopayController(UserService userService, IdeaService ideaService) {
        this.userService = userService;
        this.ideaService = ideaService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> lisAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO user) {
        UserDTO newUserDTO = userService.registerUser(user);
        return ResponseEntity.ok(newUserDTO);
    }

    @PostMapping("/users/{userId}/ideas")
    public ResponseEntity<IdeaDTO> addIdea(@RequestBody @Valid IdeaDTO ideaDTO, @PathVariable Long userId) {
        IdeaDTO newIdeaDTO = ideaService.addIdea(ideaDTO, userId);
        return ResponseEntity.ok(newIdeaDTO);
    }

    @GetMapping("/users/{userId}/ideas")
    public ResponseEntity<List<IdeaDTO>> listIdeas(@PathVariable Long userId) {
        return ResponseEntity.ok(ideaService.listIdeas(userId));
    }
}
