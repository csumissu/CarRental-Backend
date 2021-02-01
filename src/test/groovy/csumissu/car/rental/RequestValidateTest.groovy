package csumissu.car.rental

import spock.lang.Specification

import javax.validation.Validator

import static java.util.stream.Collectors.toSet
import static javax.validation.Validation.buildDefaultValidatorFactory

abstract class RequestValidateTest extends Specification {

    private static Validator validator

    def setupSpec() {
        validator = buildDefaultValidatorFactory().getValidator()
    }

    protected static <T> Set<String> validateRequest(T request, String propertyPath) {
        return validator.validate(request).stream()
                .filter({ it.propertyPath.toString() == propertyPath })
                .map({ it.getMessage() })
                .collect(toSet())
    }

    protected static boolean checkResult(Set<String> result, String errorMessage) {
        return (result.isEmpty() && errorMessage == null) || result.contains(errorMessage)
    }
}
