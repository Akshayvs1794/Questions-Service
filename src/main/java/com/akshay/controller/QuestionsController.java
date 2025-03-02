package com.akshay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.model.Question;
import com.akshay.model.QuestionWapper;
import com.akshay.model.Response;
import com.akshay.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionsController 
{
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/")
	public ResponseEntity<String> test()
	{
		String test = "<h1>Hello World</h1>";
		
		return new ResponseEntity<String>(test,HttpStatus.CREATED);
	}
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		ResponseEntity<List<Question>> questions = questionService.getAllQuestions();
		
		return questions;
	}
	
	  @GetMapping("allQuestions/{category}") 
	  public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) 
	  {
		  return questionService.getAllQuestionByCategory(category); 
	  }
	 
	
	@PostMapping("/save")
	public ResponseEntity<Question> saveQuestion(@RequestBody Question question)
	{
		ResponseEntity<Question> q = this.questionService.saveQuestion(question);
		System.out.println(q);
		
		return q;
	}
	
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<Question> editQuestion(@RequestBody Question question, @PathVariable int id)
	{
		ResponseEntity<Question> q = this.questionService.editQuestion(question, id);
		System.out.println(q);

		return q;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeQuestion(@PathVariable int id)
	{
		ResponseEntity<String> deleteQuestion = questionService.deleteQuestion(id);
		
		return deleteQuestion;
	}
	
	//generate quiz
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, 
			@RequestParam Integer numQs )
	{
		return questionService.getQuestionsForQuiz(category, numQs); 
	}
	
	// getQuestion(questionId)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
	{
		return questionService.getQuestionsFromId(questionIds);
	}
	
	//getScore
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return questionService.getScore(responses);
	}
	
	
}