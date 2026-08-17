package anh.quizapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED EXCEPTION"),
    NOT_FOUND_ID(404, "ID isn't exist")
    ;

    private final int code;
    private final String message;
}
