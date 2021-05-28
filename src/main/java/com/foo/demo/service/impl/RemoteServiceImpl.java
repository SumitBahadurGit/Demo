package com.foo.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.foo.demo.model.RemoteResponse;
import com.foo.demo.model.Result;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class RemoteServiceImpl extends AbstractRemoteService{

	@Autowired
	public RemoteServiceImpl(WebClient webClient) {
		super(webClient);		
	}

	@Override	
	public Mono<List<Result>> parallelRemoteCall(int amount, String categories) {

		List<Mono<List<Result>>> monos = new ArrayList<>();
		Arrays.asList(categories.split(","))
			.forEach(category -> {
				monos.add(remoteCall(amount, category).subscribeOn(Schedulers.elastic()));
			});

		return Mono.zip(monos, response -> Arrays.stream(response)
				.flatMap(r -> ((Collection<Result>) r).stream())
				.collect(Collectors.toList()));

	}
}
