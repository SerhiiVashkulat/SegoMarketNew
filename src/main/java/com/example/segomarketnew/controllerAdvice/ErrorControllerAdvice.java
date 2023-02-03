package com.example.segomarketnew.controllerAdvice;

import com.example.segomarketnew.controllerAdvice.Exception.CommonException;
import com.example.segomarketnew.controllerAdvice.Exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Error> handleCommonException(CommonException exception){
        return new ResponseEntity<>(Error.builder()
                .message(exception.getMessage())
                .build(),exception.getHttpStatus());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity <Error> handleUserException(RuntimeException exception) {
        return new ResponseEntity<>(Error.builder()
                .message(exception.getMessage())
                .build(),HttpStatus.NOT_FOUND );
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Error> handleBadCredentialsException(BadCredentialsException ex){
        return new ResponseEntity<>(Error.builder()
                .message(ex.getMessage())
                .build(),HttpStatus.UNAUTHORIZED);
    }


}
