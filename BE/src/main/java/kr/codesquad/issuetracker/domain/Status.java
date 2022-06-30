package kr.codesquad.issuetracker.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
	OPEN("OPEN"),
	CLOSED("CLOSED");

	private final String value;

	@JsonCreator
	public static Status from(String s) {
		return Status.valueOf(s.toUpperCase());
	}
}
