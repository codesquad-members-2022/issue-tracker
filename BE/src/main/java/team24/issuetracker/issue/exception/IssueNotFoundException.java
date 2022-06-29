package team24.issuetracker.issue.exception;

public class IssueNotFoundException extends RuntimeException {

	public IssueNotFoundException(String message) {
		super(message);
	}
}
