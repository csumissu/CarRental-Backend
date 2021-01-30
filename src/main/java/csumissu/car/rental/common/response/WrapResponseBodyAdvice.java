package csumissu.car.rental.common.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

import static csumissu.car.rental.common.response.WrapResponseBodyInterceptor.USE_CUSTOMIZED_RESPONSE;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@ControllerAdvice
public class WrapResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        var isAlreadyWrapped = returnType.getGenericParameterType().getTypeName()
                .contains(ResponseResult.class.getName());
        return !isAlreadyWrapped && Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(attributes -> attributes.getAttribute(USE_CUSTOMIZED_RESPONSE, SCOPE_REQUEST))
                .map(attribute -> Boolean.valueOf(String.valueOf(attribute)))
                .orElse(Boolean.FALSE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        return ResponseResult.success(body);
    }

}
