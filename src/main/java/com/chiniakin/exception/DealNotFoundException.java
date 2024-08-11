package com.chiniakin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение с сообщением, что сделка не найдена.
 *
 * @author ChiniakinD
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DealNotFoundException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public DealNotFoundException(String message) {
        super(message);
    }

}
