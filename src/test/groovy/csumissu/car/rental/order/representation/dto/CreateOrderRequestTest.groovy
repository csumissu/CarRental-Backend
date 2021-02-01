package csumissu.car.rental.order.representation.dto

import csumissu.car.rental.RequestValidateTest
import spock.lang.Unroll

@Unroll
class CreateOrderRequestTest extends RequestValidateTest {

    def "validate car id"() {
        given:
        def request = createOrderRequest()
        request.setCarId(carId)

        when:
        def result = validateRequest(request, "carId")

        then:
        checkResult(result, errorMessage)

        where:
        carId || errorMessage
        null  || "must not be null"
        1L    || null
    }

    def "validate booked days"() {
        given:
        def request = createOrderRequest()
        request.setBookedDays(bookedDays)

        when:
        def result = validateRequest(request, "bookedDays")

        then:
        checkResult(result, errorMessage)

        where:
        bookedDays || errorMessage
        null       || "must not be null"
        0          || "must be greater than or equal to 1"
        1          || null
    }

    private static CreateOrderRequest createOrderRequest() {
        return CreateOrderRequest.builder()
                .carId(1L)
                .bookedDays(2)
                .build()
    }

}
