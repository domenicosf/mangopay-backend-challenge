package com.mangopay.repository;

import com.mangopay.model.Idea;
import com.mangopay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
    List<Idea> findByUserOrderByDayDesc(User user);
}
