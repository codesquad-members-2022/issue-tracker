package com.team09.issue_tracker.exception;

public class MemberInvalidException extends InvalidException{

	@Override
	protected String getBodyMessage() {
		return "memberId가 올바르지 않습니다.";
	}
}
