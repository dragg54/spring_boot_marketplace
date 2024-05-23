package ecommerce.dtos.responses;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ErrorResponse {
    public String message;
    public HttpStatus status;
}
