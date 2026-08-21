package anh.quizapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_ID(1001, "ID isn't exist", HttpStatus.NOT_FOUND)
    ;

    private final int code;
    private final String message;
    private final HttpStatus status;
}
