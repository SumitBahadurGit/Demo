package com.foo.demo.service.impl;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.foo.demo.model.RemoteResponse;
import com.foo.demo.model.Result;
import com.foo.demo.service.RemoteService;

import reactor.core.publisher.Mono;

public abstract class AbstractRemoteService implements RemoteService{

	private WebClient webClient;	
	private final String AMOUNT = "amount";
	private final String CATEGORY = "category";
	
	public AbstractRemoteService(WebClient webClient) {
		this.webClient = webClient;
	}
	
	@Override
	public Mono<List<Result>> remoteCall(int amount, String category) {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add(AMOUNT, String.valueOf(amount));
		params.add(CATEGORY, category);

		return this.webClient
				.get()
				.uri(builder -> builder.path("").queryParams(params).build())
				.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(RemoteResponse.class)
				.filter(entity -> entity.getStatusCode().is2xxSuccessful() && entity.getBody() != null)
				.flatMap(entity -> Mono.just(entity.getBody().getResults()));
	}

}
