package team24.issuetracker.member.exception;

public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(String message) {
		super(message);
	}
}
