package com.sh.issuetracker.issue.search;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 조회 로직 분기처리 일어나야 하는 검색어 담당
 */
@Getter
@RequiredArgsConstructor
public enum SearchKeyType {
	ASSIGNEE("assignee"),
	AUTHOR("author"),
	MILESTONE("milestone"),
	LABEL("label"),
	NONE("none");

	private static Map<String, SearchKeyType> keys = keyMapper();
	private final String word;

	public static boolean hasKey(String key) {
		return keys.containsKey(key);
	}

	public static SearchKeyType getKey(String key) {
		return keys.get(key);
	}

	public boolean equals(String word) {
		return this.toString().equals(word);
	}

	private static Map<String, SearchKeyType> keyMapper() {
		return Arrays.stream(SearchKeyType.values())
			.collect(Collectors.toMap(
				SearchKeyType::getWord,
				SearchKeyType -> SearchKeyType));
	}
}
