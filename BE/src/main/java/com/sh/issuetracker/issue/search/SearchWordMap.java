package com.sh.issuetracker.issue.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchWordMap {
	private Map<SearchKeyType, List<String>> searchWords = new HashMap<>();

	public SearchWordMap() {
		searchWords = new HashMap<>();
		searchWords.put(SearchKeyType.AUTHOR, new ArrayList<>());
		searchWords.put(SearchKeyType.ASSIGNEE, new ArrayList<>());
		searchWords.put(SearchKeyType.MILESTONE, new ArrayList<>());
		searchWords.put(SearchKeyType.LABEL, new ArrayList<>());
	}

	public void add(SearchKeyType key, String value) {
		searchWords.get(key).add(value);
	}

	public List<String> get(SearchKeyType key) {
		return searchWords.get(key);
	}
}
