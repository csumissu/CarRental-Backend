package order

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("retrieve a order")
            request {
                method(GET())
                headers {
                    contentType(applicationJson())
                }
                url($(consumer("/orders/" + anyPositiveInt()), producer("/orders/1")))
            }
            response {
                status(OK())
                headers {
                    contentType(applicationJson())
                }
                body(
                        code: $(anyPositiveInt()),
                        message: $(anyNonBlankString()),
                        data: [
                                id            : $(anyPositiveInt()),
                                bookedAt      : $(anyNonBlankString()),
                                returnedAt    : null,
                                dailyRentPrice: $(anyDouble()),
                                bookedDays    : $(anyPositiveInt()),
                                totalPrice    : $(anyDouble()),
                                car           : [
                                        id   : $(anyPositiveInt()),
                                        model: $(anyNonBlankString())
                                ],
                                user          : [
                                        id: $(anyPositiveInt())
                                ]
                        ]
                )
            }
        },

        Contract.make {
            name("search orders")
            request {
                method(GET())
                headers {
                    contentType(applicationJson())
                }
                url("/orders") {
                    queryParameters {
                        parameter("page", $(consumer(positiveInt()), producer(0)))
                        parameter("size", $(consumer(positiveInt()), producer(10)))
                        parameter("userId", $(consumer(anyPositiveInt()), producer(1)))
                    }
                }
            }
            response {
                status(OK())
                headers {
                    contentType(applicationJson())
                }
                body(
                        code: $(anyPositiveInt()),
                        message: $(anyNonBlankString()),
                        data: [
                                content         : [
                                        [
                                                id            : $(anyPositiveInt()),
                                                userId        : $(anyPositiveInt()),
                                                carId         : $(anyPositiveInt()),
                                                bookedAt      : $(anyNonBlankString()),
                                                returnedAt    : null,
                                                dailyRentPrice: $(anyDouble()),
                                                bookedDays    : $(anyPositiveInt()),
                                                totalPrice    : $(anyDouble()),
                                        ]
                                ],
                                last            : $(anyBoolean()),
                                totalPages      : $(anyPositiveInt()),
                                totalElements   : $(anyPositiveInt()),
                                numberOfElements: $(anyPositiveInt())
                        ]
                )
            }
        }
]
