package csumissu.car.rental.common.response;

import csumissu.car.rental.common.exception.AppException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseResult {

    private static final int CODE_SUCCESS = 200;
    private static final String MESSAGE_SUCCESS = "Operation is successful";

    private Integer code;
    private String message;
    private Object data;

    public static ResponseResult success(Object data) {
        var result = new ResponseResult();
        result.code = CODE_SUCCESS;
        result.message = MESSAGE_SUCCESS;
        result.data = data;
        return result;
    }

    public static ResponseResult failure(AppException exception) {
        var result = new ResponseResult();
        result.code = exception.getCode();
        result.message = exception.getMessage();
        return result;
    }

    public static ResponseResult failure(HttpStatus status, Map<String, String> data) {
        var result = new ResponseResult();
        result.code = status.value();
        result.message = status.getReasonPhrase();
        result.data = data;
        return result;
    }
}
