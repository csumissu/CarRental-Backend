package csumissu.car.rental.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    public AppException(int code, String message) {
        this(code, message, null);
    }

    public AppException(int code, String message, Throwable cause) {
        super(format(code, message), cause);
        this.code = code;
        this.message = message;

        var statusCode = Integer.parseInt(String.valueOf(code).substring(0, 3));
        this.httpStatus = HttpStatus.valueOf(statusCode);
    }

    private static String format(int code, String message) {
        return String.format("%d: %s", code, message);
    }

}
