package ru.backend.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends JsonProcessingException {
    public UnprocessableEntity(String msg) {
        super(msg);
    }
}
