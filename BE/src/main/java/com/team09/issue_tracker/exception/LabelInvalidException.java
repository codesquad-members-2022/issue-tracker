package com.team09.issue_tracker.exception;

public class LabelInvalidException extends InvalidException{

	@Override
	protected String getBodyMessage() {
		return "labelId가 올바르지 않습니다.";
	}
}
