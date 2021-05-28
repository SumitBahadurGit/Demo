package com.foo.demo.service;

import java.util.List;

import com.foo.demo.model.Result;

import reactor.core.publisher.Mono;

public interface RemoteService {

	Mono<List<Result>> parallelRemoteCall(int amount, String categories);
	Mono<List<Result>> remoteCall(int amount, String category);

}
