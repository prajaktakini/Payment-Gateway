package com.example.paymentgateway.handler.paymodes;

import com.example.paymentgateway.error.PaymentGatewayException;
import com.example.paymentgateway.handler.clients.ClientInterface;
import com.example.paymentgateway.handler.clients.ClientsHandler;
import com.example.paymentgateway.model.Client;
import com.example.paymentgateway.model.PayMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class PayModeInterfaceImpl implements PayModesInterface {

    private final PayModesHandler payModesHandler;

    private final ClientInterface clientInterface;

    /**
     * Find all pay modes supported by payment gateway
     * @return Set of supported payment gateways
     */
    @Override
    public Set<PayMode> listSupportedPayModes() {
        return payModesHandler.getSupportedPayModes().values().stream().collect(Collectors.toSet());
    }

    /**
     * Find all client specific pay modes
     * @return Set of client specific pay modes
     */
    @Override
    public Set<PayMode> listSupportedPayModes(final Client client) {
        return payModesHandler.getClientSpecificPayModes(client.getName().toUpperCase());
    }

    /**
     * Registers this paymode on payment gateway. If this paymode already exists,
     * then it throws exception with conflict error
     * @param payMode Paymode to be registered on payment gateway
     */
    @Override
    public void addSupportForPayMode(final PayMode payMode) {
        log.info("Registering a new paymode {} on payment gateway", payMode.getName().toUpperCase());
        if (payModesHandler.getSupportedPayModes().containsKey(payMode.getName().toUpperCase())) {
            throw new PaymentGatewayException(String.format("%s payment mode is already registered on payment gateway",
                    payMode.getName().toUpperCase()), HttpStatus.CONFLICT, null).logError(log);
        }
        payModesHandler.registerPayMode(payMode);
        log.info("Successfully registered a new payment mode {} on payment gateway", payMode.getName().toUpperCase());
    }

    /**
     * Registers this paymode for a given client
     * If this paymode is not supported by payment gateway, throws BAD_REQUEST exception
     * else registers it with client if client exists
     * @param payMode Paymode to be registered on payment gateway
     */
    @Override
    public void addSupportForPayMode(final PayMode payMode,
                                     final Client client) {
        log.info("Adding support of paymode {} for the client {}",
                payMode.getName().toUpperCase(), client.getName().toUpperCase());
        // First check if given paymode is already registered on the Payment Gateway. If not supported, throw error
        if (!payModesHandler.getSupportedPayModes().containsKey(payMode.getName().toUpperCase())) {
            throw new PaymentGatewayException(String.format("%s payment mode is not supported by payment gateway",
                    payMode.getName().toUpperCase()), HttpStatus.BAD_REQUEST, null).logError(log);
        }

        // Then verify if the client is legit. If not, throw exception. (Not added this check due to time constraints)
        if (!clientInterface.hasClient(client)) {
            // Throw exception
            throw new PaymentGatewayException(String.format("%s client not found",
                    client.getName().toUpperCase()), HttpStatus.NOT_FOUND, null).logError(log);
        }

        // Here, we have already verified if paymode is supported by payment gateway. Proceed with registering it with client
        payModesHandler.registerClientSpecificPayMode(client.getName().toUpperCase(), payMode);
        log.info("Successfully registered paymode {} against the client {}",
                payMode.getName().toUpperCase(), client.getName().toUpperCase());
    }

    /**
     * Remove this paymode from the clients and payment gateway
     * @param payMode Payment mode to be removed
     */
    @Override
    public void removePayMode(final PayMode payMode) {
        log.info("Removing paymode {} from clients as well as payment gateway", payMode.getName().toUpperCase());
        // First remove paymode from all clients
        payModesHandler.removePayModeFromAllClients(payMode);

        // Now remove it from payment gateway as well
        payModesHandler.removePayModeFromGateway(payMode.getName().toUpperCase());
        log.info("Successfully removed paymode {} from clients as well as payment gateway", payMode.getName().toUpperCase());

    }
}
