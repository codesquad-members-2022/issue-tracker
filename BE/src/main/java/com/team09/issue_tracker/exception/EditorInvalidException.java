package com.team09.issue_tracker.exception;

public class EditorInvalidException extends InvalidException{

	@Override
	protected String getBodyMessage() {
		return "편집할 수 있는 사용자가 아닙니다.";
	}
}
