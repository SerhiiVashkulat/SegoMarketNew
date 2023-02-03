package com.example.segomarketnew.util;

import com.example.segomarketnew.controllerAdvice.Exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.validation.ConstraintViolation;

import javax.validation.Validator;
import java.util.Set;

import static com.example.segomarketnew.domain.Code.Code.REQUEST_VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class ValidationUtil {
    private final Validator validator;

    public <T> void validation(T request) {
        if (request != null) {
            Set<ConstraintViolation<T>> result = validator.validate(request);
            if (!result.isEmpty()) {
                String resultValidation = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
                throw CommonException.builder()
                        .code(REQUEST_VALIDATION_ERROR)
                        .message(resultValidation)
                        .httpStatus(BAD_REQUEST)
                        .build();
            }
        }
    }
}
