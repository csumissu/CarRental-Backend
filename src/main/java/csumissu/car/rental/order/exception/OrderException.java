package csumissu.car.rental.order.exception;

import csumissu.car.rental.common.exception.AppException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class OrderException extends AppException {

    private OrderException(int code, String message) {
        super(code, message);
    }

    private OrderException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public static OrderException bookedDaysInvalid() {
        return new OrderException(400_02_001, "Booked days should be greater than or equal to 1");
    }

    public static OrderException notFound() {
        return new OrderException(404_02_001, "Order not found");
    }

    public static OrderException alreadyClosed() {
        return new OrderException(400_02_002, "Order was already closed");
    }

}
