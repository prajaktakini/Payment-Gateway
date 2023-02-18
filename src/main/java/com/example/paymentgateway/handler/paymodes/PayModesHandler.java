package com.example.paymentgateway.handler.paymodes;

import com.example.paymentgateway.model.PayMode;

import java.util.Map;
import java.util.Set;

public class PayModesHandler {

    private Map<String, PayMode> supportedPayModes;

    private Map<String, Set<PayMode>> clientSpecificPayModes;


    public Map<String, PayMode> getSupportedPayModes() {
        return supportedPayModes;
    }

    public Set<PayMode> getClientSpecificPayModes(String clientName) {
        return clientSpecificPayModes.get(clientName);
    }

    public void registerPayMode(PayMode payMode) {
        String payModeName = payMode.getName().toUpperCase();
        supportedPayModes.put(payModeName, payMode);
    }

    public void registerClientSpecificPayMode(String clientName, PayMode payMode) {
        String client = clientName.toUpperCase();
        Set<PayMode> clientSupportedPayModes = clientSpecificPayModes.get(client);
        clientSupportedPayModes.add(payMode);
        clientSpecificPayModes.put(client, clientSupportedPayModes);
    }

    public void removePayModeFromAllClients(PayMode payMode) {
        for (String client : clientSpecificPayModes.keySet()) {
            clientSpecificPayModes.get(client.toUpperCase()).remove(payMode);
        }
    }

    public void removePayModeFromGateway(String payMode) {
        supportedPayModes.remove(payMode.toUpperCase());
    }
}
