package kr.codesquad.issuetracker.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
	OPEN("open"),
	CLOSED("closed");

	private final String value;
}
