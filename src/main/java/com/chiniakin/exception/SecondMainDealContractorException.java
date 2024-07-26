package com.chiniakin.exception;

/**
 * Исключение с сообщением, что у сделки не может быть больше одного главного контрагента.
 *
 * @author ChiniakinD
 */
public class SecondMainDealContractorException extends RuntimeException {

    /**
     * @param message сообщение об ошибке.
     */
    public SecondMainDealContractorException(String message) {
        super(message);
    }

}
