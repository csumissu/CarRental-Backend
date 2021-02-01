package order

import org.springframework.cloud.contract.spec.Contract

[
        Contract.make {
            name("close an order")
            request {
                method(POST())
                url($(consumer("/orders/" + anyPositiveInt() + "/close"), producer("/orders/1/close")))
                headers {
                    contentType(applicationJson())
                }
            }
            response {
                status(OK())
                headers {
                    contentType(applicationJson())
                }
            }
        }
]