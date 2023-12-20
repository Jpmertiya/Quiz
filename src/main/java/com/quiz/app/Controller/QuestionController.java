package com.quiz.app.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.Entity.Question;
import com.quiz.app.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionService questionService;

	@GetMapping("/allQuestion")
	public List<Question> getAllQuestions() {
		return questionService.getAllQuestion();
	}

	@GetMapping("/category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category) {
		logger.info(category + " yes it's working");
		return questionService.getAllQuestionByCategory(category);
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveQuestion(@RequestBody Question question) {
		questionService.save(question);
		return ResponseEntity.status(HttpStatus.CREATED).body("successfully save to db");
	}

	@DeleteMapping("/delete/{delete}")
	public ResponseEntity<?> deleteQuestion(@PathVariable String delete) {
		int id = 0;
		try {
			id = Integer.valueOf(delete);
			if (id == 0)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			logger.info("unable to convert string into int");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		questionService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
