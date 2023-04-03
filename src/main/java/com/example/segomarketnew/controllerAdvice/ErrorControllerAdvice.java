package com.example.segomarketnew.controllerAdvice;

import com.example.segomarketnew.controllerAdvice.Exception.CommonException;
import com.example.segomarketnew.controllerAdvice.Exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Error> handleCommonException(CommonException exception){
        return new ResponseEntity<>(Error.builder()
                .message(exception.getMessage())
                .build(),exception.getHttpStatus());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity <Error> handleException(RuntimeException exception) {
        return new ResponseEntity<>(Error.builder()
                .message(exception.getMessage())
                .build(),HttpStatus.BAD_REQUEST );
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Error> handleBadCredentialsException(BadCredentialsException ex){
        return new ResponseEntity<>(Error.builder()
                .message(ex.getMessage())
                .build(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        return new ResponseEntity<>(Error.builder()
                        .message("Email or password not valid")
                .build(),HttpStatus.BAD_REQUEST );
    }



}
