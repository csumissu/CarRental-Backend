package csumissu.car.rental.common.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    private final int code;
    private final String message;

    public AppException(int code, String message) {
        super(format(code, message));
        this.code = code;
        this.message = message;
    }

    public AppException(int code, String message, Throwable cause) {
        super(format(code, message), cause);
        this.code = code;
        this.message = message;
    }

    private static String format(int code, String message) {
        return String.format("%d: %s", code, message);
    }

}
