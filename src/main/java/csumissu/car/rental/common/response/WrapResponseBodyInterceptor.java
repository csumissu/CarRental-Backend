package csumissu.car.rental.common.response;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class WrapResponseBodyInterceptor implements HandlerInterceptor, WebMvcConfigurer {

    public static final String USE_CUSTOMIZED_RESPONSE = "use-customized-response";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            var handlerMethod = (HandlerMethod) handler;
            var clazz = handlerMethod.getBeanType();
            var method = handlerMethod.getMethod();

            var annotationExists = clazz.isAnnotationPresent(UseCustomizedResponse.class)
                    || method.isAnnotationPresent(UseCustomizedResponse.class);
            request.setAttribute(USE_CUSTOMIZED_RESPONSE, annotationExists);
        }
        return true;
    }
}
