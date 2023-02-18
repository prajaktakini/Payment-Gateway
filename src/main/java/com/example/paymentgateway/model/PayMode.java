package com.example.paymentgateway.model;

import lombok.Data;

import java.util.Map;

@Data
public class PayMode {

    // Example could be UPI, NETBANKING, CREDIT_CARD, DEBIT_CARD
    private String name;

    private String description;

    /**
     * Below map contains paymode specific details.
     * Note that: Actual keys are not defined for each paymode due to time limitations
     * In real world, for each paymode, we should define well-define keys such as for netbanking -> username, password
     * for credit cards -> Name of user printed on card, card number, CVV, expiry etc
     */
    private Map<String, String> details;

}

