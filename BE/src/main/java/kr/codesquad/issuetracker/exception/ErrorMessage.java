package kr.codesquad.issuetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

	//400 BAD_REQUEST
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

	//404 NOT_FOUND
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."),
	ACCESS_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "엑세스 토큰을 찾을 수 없습니다."),
	GITHUB_USER_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "GITHUB 유저 정보를 찾을 수 없습니다."),

	//500
	UNIQUE_CONSTRAINT_VIOLATED(HttpStatus.INTERNAL_SERVER_ERROR, "해당 라벨이 다른 이슈에 사용되고있어서 삭제할수 없습니다."),
	NOT_MATCH_HTTP_HEADER_FORMAT(HttpStatus.INTERNAL_SERVER_ERROR, "해당 헤더의 형식이 맞지 않습니다."),

	//501
	LABEL_NOT_SAVE(HttpStatus.NOT_IMPLEMENTED, "라벨을 저장할 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
