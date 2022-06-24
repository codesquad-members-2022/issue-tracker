package kr.codesquad.issuetracker.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
	OPEN("OPEN"),
	CLOSED("CLOSED");

	private final String value;
}
