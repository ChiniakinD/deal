package com.chiniakin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик исключений.
 *
 * @author ChiniakinD
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывет исключение {@link SecondMainDealContractorException} .
     *
     * @param e исключение {@link SecondMainDealContractorException} .
     * @return ответ с сообщением об ошибке и код статуса 409.
     */
    @ExceptionHandler(SecondMainDealContractorException.class)
    public ResponseEntity<Object> handleSecondMainDealContractorException(SecondMainDealContractorException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
