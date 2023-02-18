package com.example.paymentgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Client {

    // Example of client name could be FLIPKART, AMAZON, RELIANCE_DIGITAL, CROMA, etc
    private String name;

    private String description;
}
