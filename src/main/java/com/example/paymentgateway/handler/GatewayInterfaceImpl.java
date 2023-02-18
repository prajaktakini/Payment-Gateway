package com.example.paymentgateway.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class GatewayInterfaceImpl implements GatewayInterface {

    private final String KEY_AMOUNT = "amount";

    /**
     * Below method mocks actual payment procedure.
     * Note that, because of time restrictions, algo could not be implemented
     * @param payMode Payment mode selected for the payment
     * @param clientName Client requesting the payment
     * @param details Payment details map containing details such as amount,
     *                paymode specific details such as username, password,
     *                whereas for cards, card details etc
     * Note that: Details map exposes sensitive info in String, in real world, this info should not be in plain text
     *                but should be encrypted and pass in char[] array instead of in String
     */
    @Override
    public void makePayment(final String payMode,
                            final String clientName,
                            final Map<String, String> details) {
        log.info("Making a payment of Rs. {} from client {} using pay mode {}",
                details.get(KEY_AMOUNT), clientName, payMode);
    }
}
