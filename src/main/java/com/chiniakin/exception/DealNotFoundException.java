package com.chiniakin.exception;

/**
 * Исключение с сообщением, что сделка не найдена.
 *
 * @author ChiniakinD
 */
public class DealNotFoundException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public DealNotFoundException(String message) {
        super(message);
    }

}
