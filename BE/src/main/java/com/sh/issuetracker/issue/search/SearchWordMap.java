package com.sh.issuetracker.issue.search;

import org.apache.logging.log4j.util.Strings;

import java.util.HashMap;
import java.util.Map;

public class SearchWordMap {
	private Map<SearchKeyType, String> searchWords = new HashMap<>();

	public SearchWordMap() {
		searchWords = new HashMap<>();
		searchWords.put(SearchKeyType.AUTHOR, null);
		searchWords.put(SearchKeyType.ASSIGNEE, null);
		searchWords.put(SearchKeyType.MILESTONE, null);
		searchWords.put(SearchKeyType.LABEL, null);
	}

	public void add(SearchKeyType key, String value) {
		searchWords.replace(key, value);
	}

	public String get(SearchKeyType key) {
		return searchWords.get(key);
	}

	public boolean alreadyHasValue(SearchKeyType key) {
		return Strings.isBlank(searchWords.get(key));
	}
}
