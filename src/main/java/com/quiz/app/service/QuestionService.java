package com.quiz.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.app.Dao.QuestionDao;
import com.quiz.app.Entity.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao dao;

	public List<Question> getAllQuestion() {
		return dao.findAll();
	}

	public List<Question> getAllQuestionByCategory(String category) {
		return dao.findByCategory(category);
	}

	public boolean save(Question question) {
		Question save = dao.save(question);

		return save != null ? true : false;
	}

	public void delete(int id) {
		dao.deleteById(id);
	}

}
