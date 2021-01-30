package csumissu.car.rental.common.exception;

import csumissu.car.rental.common.response.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseResult> handleException(AppException exception) {
        var body = ResponseResult.failure(exception);
        return new ResponseEntity<>(body, exception.getHttpStatus());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseResult> handleException(MethodArgumentNotValidException exception) {
        var status = HttpStatus.BAD_REQUEST;
        var data = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError ->
                        Optional.ofNullable(fieldError.getDefaultMessage()).orElse("no message")));
        var body = ResponseResult.failure(status, data);
        return new ResponseEntity<>(body, status);
    }

}
