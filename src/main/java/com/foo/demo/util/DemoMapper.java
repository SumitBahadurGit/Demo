package com.foo.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.foo.demo.model.DemoQuiz;
import com.foo.demo.model.DemoResponse;
import com.foo.demo.model.DemoResult;
import com.foo.demo.model.Result;

public class DemoMapper {

	private static Function<Result, DemoResult> transformFunc = x -> {
		
		DemoResult result = new DemoResult();
		result.setType(x.getType());
		result.setDifficulty(x.getDifficulty());
		result.setQuestion(x.getQuestion());
		result.setCorrect_answer(x.getCorrect_answer());
		result.setAll_answers(new ArrayList<>());
		result.getAll_answers().add(x.getCorrect_answer());
		result.getAll_answers().addAll(x.getIncorrect_answers());
		return result;
	};
	
	public static DemoResponse mapDataToResponse (List<Result> remoteResult) {
		
		DemoResponse response = new DemoResponse();
		response.setQuiz(new ArrayList<>());
		
		Map<String, List<Result>> groupedMap = remoteResult
				.stream()
				.collect(Collectors.groupingBy(Result::getCategory));

		for(String key: groupedMap.keySet()) {
			List<DemoResult> results = groupedMap.get(key)
					.stream().map(x -> transformFunc.apply(x))
					.collect(Collectors.toList());
			response.getQuiz().add(new DemoQuiz(key, results));
			
		}
		return response;
	}
}
