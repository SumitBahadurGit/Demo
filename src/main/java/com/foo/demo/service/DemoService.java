package com.foo.demo.service;

import com.foo.demo.model.DemoResponse;

import reactor.core.publisher.Mono;

public interface DemoService {

	Mono<DemoResponse> getQuiz(int amount, String categories);

}
