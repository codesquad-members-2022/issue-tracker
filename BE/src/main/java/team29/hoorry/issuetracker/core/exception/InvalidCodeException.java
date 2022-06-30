package team29.hoorry.issuetracker.core.exception;

public class InvalidCodeException extends AuthException {

	public InvalidCodeException(String message) {
		super(message);
	}

	public InvalidCodeException(String message, Throwable cause) {
		super(message, cause);
	}
}
