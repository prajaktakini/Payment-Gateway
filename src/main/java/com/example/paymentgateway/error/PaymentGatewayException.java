package com.example.paymentgateway.error;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

@Slf4j
public class PaymentGatewayException extends RuntimeException {

    private HttpStatus httpStatus;


    public PaymentGatewayException(final String errorMessage,
                                   final Throwable cause) {
        super(errorMessage, cause);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PaymentGatewayException(final String errorMessage,
                                   final HttpStatus httpStatus,
                                   final Throwable cause) {
        super(errorMessage, cause);
        this.httpStatus = httpStatus;
    }

    public PaymentGatewayException logError(Logger logger) {
        if (logger == null) {
            logger = log;
        }
        logger.info("ERROR: {}", getMessage());
        return this;
    }
}
