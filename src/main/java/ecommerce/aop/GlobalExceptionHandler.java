package ecommerce.aop;

import ecommerce.exceptions.DuplicateException;
import ecommerce.exceptions.InvalidRequestException;
import ecommerce.exceptions.NotFoundException;
import ecommerce.dtos.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException exception, WebRequest request){
        ErrorResponse errorMessage = new ErrorResponse( exception.getMessage(), HttpStatus.NOT_FOUND) {
        };
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalServerException(Exception exception, WebRequest request){
        ErrorResponse errorMessage = new ErrorResponse( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR) {
        };
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((errorMessage));
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> duplicateException(DuplicateException exception, WebRequest request){
        ErrorResponse errorMessage = new ErrorResponse( exception.getMessage(), HttpStatus.CONFLICT) {
        };
        return ResponseEntity.status(HttpStatus.CONFLICT).body((errorMessage));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> invalidRequestException(InvalidRequestException exception, WebRequest request){
        ErrorResponse errorMessage = new ErrorResponse( exception.getMessage(), HttpStatus.BAD_REQUEST) {
        };
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((errorMessage));
    }
}
