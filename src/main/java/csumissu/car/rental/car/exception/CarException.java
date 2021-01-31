package csumissu.car.rental.car.exception;

import csumissu.car.rental.common.exception.AppException;

public class CarException extends AppException {

    private CarException(int code, String message) {
        super(code, message);
    }

    private CarException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public static CarException modelInvalid() {
        return new CarException(400_01_001, "Model should not be blank");
    }

    public static CarException dailyRentPriceInvalid() {
        return new CarException(400_01_002, "Daily rent price should greater than 0");
    }

    public static CarException notFound() {
        return new CarException(404_01_001, "Car not found");
    }

    public static CarException alreadyInUse() {
        return new CarException(400_01_003, "This car is already in use");
    }

    public static CarException alreadyAvailable() {
        return new CarException(400_01_004, "This car is already available");
    }
}
