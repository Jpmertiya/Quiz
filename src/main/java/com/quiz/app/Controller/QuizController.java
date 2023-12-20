package com.quiz.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.Entity.Response;
import com.quiz.app.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam int numQ,
			@RequestParam String title) {

		return quizService.createQuiz(category, numQ, title);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getQuizById(@PathVariable String id){
		
		int Qid=0;
		try {
			Qid=Integer.valueOf(id);
			if (Qid==0) {
				return new ResponseEntity<>("invalid id",HttpStatus.BAD_REQUEST);
			}
			return quizService.getQuiz(Qid);
		} catch (Exception e) {
			return new ResponseEntity<>("invalid id",HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<?> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
		return quizService.calculateResult(id,response);
	}

}
