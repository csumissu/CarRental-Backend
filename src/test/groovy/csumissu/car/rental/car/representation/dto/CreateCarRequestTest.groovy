package csumissu.car.rental.car.representation.dto

import csumissu.car.rental.RequestValidateTest
import spock.lang.Unroll

@Unroll
class CreateCarRequestTest extends RequestValidateTest {

    def "validate model"() {
        given:
        def request = createCarRequest()
        request.setModel(model)

        when:
        def result = validateRequest(request, "model")

        then:
        checkResult(result, errorMessage)

        where:
        model || errorMessage
        null  || "must not be blank"
        " "   || "must not be blank"
        "Any" || null
    }

    def "validate dailyRentPrice"() {
        given:
        def request = createCarRequest()
        request.setDailyRentPrice(dailyRentPrice)

        when:
        def result = validateRequest(request, "dailyRentPrice")

        then:
        checkResult(result, errorMessage)

        where:
        dailyRentPrice  || errorMessage
        null            || "must not be null"
        BigDecimal.ZERO || "must be greater than 0.00"
        BigDecimal.ONE  || null
    }

    private static CreateCarRequest createCarRequest() {
        return CreateCarRequest.builder()
                .model("BMW 650")
                .dailyRentPrice(new BigDecimal("1.00"))
                .build()
    }
}
