package anh.quizapp.exception;

import anh.quizapp.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handlingException(Exception e) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        log.error(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage(), e);
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatus()).body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAppException(AppException e) {
        ErrorCode error = e.getErrorCode();

        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setCode(error.getCode());
        apiResponse.setMessage(error.getMessage());

        return ResponseEntity.status(error.getStatus()).body(apiResponse);

    }
}
