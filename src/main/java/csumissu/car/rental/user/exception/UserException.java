package csumissu.car.rental.user.exception;

import csumissu.car.rental.common.exception.AppException;

public class UserException extends AppException {

    private UserException(int code, String message) {
        super(code, message);
    }

    private UserException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public static UserException notFound() {
        return new UserException(404_00_001, "User not found");
    }
}