package com.akshay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.akshay.model.Question;

@Repository
public interface QuestionsDao extends JpaRepository<Question, Integer> {

	public List<Question> findByCategory(String category);

	@Query(value = "select q.id from question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ;", nativeQuery = true)
	public List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}