package com.example.paymentgateway.handler;

import com.example.paymentgateway.model.Client;
import org.springframework.lang.NonNull;

import java.util.Map;

public interface GatewayInterface {

    public void makePayment(String payMode, String clientName, Map<String, String> details);

}
