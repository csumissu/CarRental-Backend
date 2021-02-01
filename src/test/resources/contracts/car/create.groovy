package car

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("create new car")
            request {
                method(POST())
                url("/cars")
                headers {
                    contentType(applicationJson())
                }
                body(
                        model: $(consumer(anyNonBlankString()), producer("BMW 650")),
                        dailyRentPrice: $(consumer(anyDouble()), producer("100.00"))
                )
            }
            response {
                status(CREATED())
                headers {
                    contentType(applicationJson())
                }
                body(
                        code: $(anyPositiveInt()),
                        message: $(anyNonBlankString()),
                        data: [
                                id: $(anyPositiveInt())
                        ]
                )
            }
        }
]
