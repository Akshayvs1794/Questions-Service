package com.akshay.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.akshay.dao.QuestionsDao;
import com.akshay.model.Question;
import com.akshay.model.QuestionWapper;
import com.akshay.model.Response;

@Service
public class QuestionService
{
	@Autowired
	QuestionsDao questionsDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> questions = questionsDao.findAll();

		if(questions.size()<=0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Question>>(questions,HttpStatus.OK);
	}

	public ResponseEntity<Question> saveQuestion(Question question)
	{
		Question q = questionsDao.save(question);

		return new ResponseEntity<>(q,HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteQuestion(int id)
	{
		questionsDao.deleteById(id);
		
		return new ResponseEntity<String>("1 record deleted ..",HttpStatus.OK);
	}

	public ResponseEntity<Question> editQuestion(Question question, int id)
	{
		Optional<Question> ques = questionsDao.findById(id);
		if(ques.isPresent())
		{
			questionsDao.save(question);
		}else {
			return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	} 
	  
	  
	  public ResponseEntity<List<Question>> getAllQuestionByCategory(String category)
	  {
		  List<Question> categoryQuestion = questionsDao.findByCategory(category);
	  
		  return new ResponseEntity<List<Question>>(categoryQuestion,HttpStatus.OK);
	 }

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQs)
	{
		List<Integer> questions = questionsDao.findRandomQuestionsByCategory(category, numQs);
		
		return new ResponseEntity<List<Integer>>(questions,HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWapper>> getQuestionsFromId(List<Integer> questionIds)
	{
		List<QuestionWapper> wrappers = new ArrayList<>();
		
		List<Question> questions = new ArrayList<>();
		
		for(Integer id : questionIds) {
			questions.add(questionsDao.findById(id).get());
		}
		
		for(Question question : questions)
		{
			QuestionWapper wrapper = new QuestionWapper();
			
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			
			wrappers.add(wrapper);
			
		}
		
		return new ResponseEntity<List<QuestionWapper>>(wrappers ,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses)
	{
		Integer right = 0;
		
		for(Response response : responses)
		{
			Question question = questionsDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
			{
				right++;
			}
		}
		
		return new ResponseEntity<Integer>(right ,HttpStatus.OK);
	}
}