package com.quiz.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.Dao.QuestionDao;
import com.quiz.app.Dao.QuizDao;
import com.quiz.app.Entity.Question;
import com.quiz.app.Entity.QuestionWrapper;
import com.quiz.app.Entity.Quiz;
import com.quiz.app.Entity.Response;

@Service
public class QuizService {

	@Autowired
	private QuizDao Qdao;

	@Autowired
	private QuestionDao dao;

	public ResponseEntity<?> createQuiz(String category, int numQ, String title) {

		try {
			List<Question> list = dao.findRandomQuestionsByCategory(category, numQ);

			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestion(list);
			Qdao.save(quiz);
			return new ResponseEntity<>("successfully saved to quiz", HttpStatus.CREATED);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>("Invalid data provided", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {

		Optional<Quiz> findById = Qdao.findById(id);
		List<Question> list = findById.get().getQuestion();
		List<QuestionWrapper> questionWrappers = new ArrayList<>();

		for (Question li : list) {
			QuestionWrapper qw = new QuestionWrapper();
			qw.setId(li.getId());
			qw.setQuestionTitle(li.getQuestionTitle());
			qw.setOption1(li.getOption1());
			qw.setOption2(li.getOption2());
			qw.setOption3(li.getOption3());
			qw.setOption4(li.getOption4());
			questionWrappers.add(qw);
		}
		return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
	}

	public ResponseEntity<?> calculateResult(Integer id, List<Response> response) {
		Optional<Quiz> findById = Qdao.findById(id);
		List<Question> list = findById.get().getQuestion();
		int right = 0, i = 0;
		for (Response res : response) {
			if (res.getResponse().equals(list.get(i).getRightAnswer())) {
				right++;
			}
			i++;
		}

		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
