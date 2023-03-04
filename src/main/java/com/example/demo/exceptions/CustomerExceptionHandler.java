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
import java.util.List;
import java.util.stream.Collectors;


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
        /*
        ex.getFieldErrors().stream().findFirst().get();

        The above is used to get the first error in case of an exception and return the same.
        In case of multiple errors as well, this would return the first error.
         */

        /*
        Below list of errors is to capture all possible errors and return them
         */
        List<String> errors = ex.getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());


        ErrorResponse errorResponse = ErrorResponse.builder().code(status.value()).timeStamp(LocalDateTime.now())
                // Below line of ex.getMessage will get the list of errors and print the same
                //.message(ex.getMessage())
                //.message(ex.getAllErrors().toString())


                .message(errors)
             //   .message(ex.getFieldErrors().stream().findFirst().get().getDefaultMessage())
                .details(request.getDescription(false)).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
