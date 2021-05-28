package com.foo.demo.model;

import java.util.List;

public class DemoQuiz {

	private String category;
	private List<DemoResult> results;
			
	public DemoQuiz() {
		super();
	}
	
	public DemoQuiz(String category, List<DemoResult> results) {
		super();
		this.category = category;
		this.results = results;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<DemoResult> getResults() {
		return results;
	}
	public void setResults(List<DemoResult> results) {
		this.results = results;
	}
	
	
}
