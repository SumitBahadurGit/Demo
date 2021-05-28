package com.foo.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foo.demo.model.DemoResponse;
import com.foo.demo.service.DemoService;
import com.foo.demo.service.RemoteService;
import com.foo.demo.util.DemoMapper;

import reactor.core.publisher.Mono;

@Component
public class DemoServiceImpl implements DemoService {
	
	private RemoteService service;
		
	@Autowired
	public DemoServiceImpl(RemoteService service) {
		this.service = service;
	}
		
	@Override
	public Mono<DemoResponse> getQuiz(int amount, String categories) {
		
		return service
				.parallelRemoteCall(amount, categories)
				.flatMap(x -> 
						Mono.just(DemoMapper.mapDataToResponse(x))
				);
				
	}
	

}
