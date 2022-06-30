package team29.hoorry.issuetracker.core.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

	public static final String NO_TOKEN_MESSAGE = "토큰이 존재하지 않습니다.";
	public static final String INVALID_TOKEN_MESSAGE = "토큰이 유효하지 않습니다.";
	public static final String INVALID_CODE_MESSAGE = "코드가 유효하지 않습니다.";
	public static final String NO_MEMBER_MESSAGE = "해당 memberId를 가진 멤버는 없습니다.";

}
