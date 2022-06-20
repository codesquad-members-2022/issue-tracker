package codesquad.issuetracker.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseMessage {

    private final HttpStatus status;
    private final String message;

    public ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
