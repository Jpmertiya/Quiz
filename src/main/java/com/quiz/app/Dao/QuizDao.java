package com.quiz.app.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.app.Entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
