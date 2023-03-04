package com.example.demo.exceptions;

import com.example.demo.model.ErrorResponse;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;



/*

We are overdiding the Response Entity class and having a custom exception instead of a full message whic is unnecessary
 */
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {


    /*

    Printing only the custom message with status code, time stamp, exception message and returning with 400 Bad request error
     */

    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder().code(status.value()).timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
