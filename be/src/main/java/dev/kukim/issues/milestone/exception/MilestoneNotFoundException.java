package dev.kukim.issues.milestone.exception;

import dev.kukim.issues.common.exception.ElementNotFoundException;

public class MilestoneNotFoundException extends ElementNotFoundException {

	public MilestoneNotFoundException() {
		super("마일스톤을 찾을 수 없습니다.");
	}
}
