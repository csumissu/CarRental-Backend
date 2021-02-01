package order

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("create new order")
            request {
                method(POST())
                url("/orders")
                headers {
                    contentType(applicationJson())
                }
                body(
                        carId: $(consumer(anyPositiveInt()), producer(1)),
                        bookedDays: $(consumer(anyPositiveInt()), producer(2))
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
