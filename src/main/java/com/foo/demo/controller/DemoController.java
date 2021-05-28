package com.foo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foo.demo.model.DemoResponse;
import com.foo.demo.service.DemoService;

import reactor.core.publisher.Mono;

@RestController
public class DemoController {

	private DemoService service;
	
	@Autowired
	public DemoController(DemoService service) {
		this.service = service;
	}
		
	@GetMapping("/coding/exercise/quiz")
	private Mono<DemoResponse> getQuiz(
			@RequestParam(required = true, defaultValue = "5") int amount,
			@RequestParam(required = true, defaultValue = "11,12") String categories){
		
		return service.getQuiz(amount, categories);
	}

}
