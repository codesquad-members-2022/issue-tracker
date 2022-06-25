package com.team09.issue_tracker.exception;

public class MilestoneInvalidException extends InvalidException {

	@Override
	protected String getBodyMessage() {
		return "milestoneId가 올바르지 않습니다.";
	}
}
