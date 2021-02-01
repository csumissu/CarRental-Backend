package car

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("retrieve a car")
            request {
                method(GET())
                headers {
                    contentType(applicationJson())
                }
                url($(consumer("/cars/" + anyPositiveInt()), producer("/cars/1")))
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
                                model         : $(anyNonBlankString()),
                                dailyRentPrice: $(anyDouble()),
                                status        : $(anyPositiveInt())
                        ]
                )
            }
        },

        Contract.make {
            name("search cars")
            request {
                method(GET())
                headers {
                    contentType(applicationJson())
                }
                url("/cars") {
                    queryParameters {
                        parameter("page", $(consumer(positiveInt()), producer(0)))
                        parameter("size", $(consumer(positiveInt()), producer(10)))
                        parameter("status", $(consumer(anyPositiveInt()), producer(1)))
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
                                                model         : $(anyNonBlankString()),
                                                dailyRentPrice: $(anyDouble()),
                                                status        : $(anyPositiveInt())
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
