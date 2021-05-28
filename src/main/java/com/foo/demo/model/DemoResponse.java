package com.foo.demo.model;

import java.util.List;

public class DemoResponse {

	List<DemoQuiz> quiz;
	
	public DemoResponse() {
		super();
	}

	public List<DemoQuiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<DemoQuiz> quiz) {
		this.quiz = quiz;
	}
	
	
}
