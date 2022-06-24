package com.sh.issuetracker.issue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IssueStatus {
	OPEN("open"), CLOSE("closed");

	private final String text;
	private static Map<String, IssueStatus> valueMap = toValueMap();

	public static boolean hasValue(String value) {
		return valueMap.containsKey(value);
	}

	public static IssueStatus from(String value) {
		return valueMap.get(value);
	}

	public IssueStatus changeAnother() {
		if (this == OPEN) {
			return CLOSE;
		}
		return OPEN;
	}

	public boolean isOpen() {
		return this == OPEN;
	}

	private static Map<String, IssueStatus> toValueMap() {
		return Arrays.stream(IssueStatus.values())
			.collect(Collectors.toMap(
				IssueStatus::getText,
				IssueStatus -> IssueStatus));
	}
}
