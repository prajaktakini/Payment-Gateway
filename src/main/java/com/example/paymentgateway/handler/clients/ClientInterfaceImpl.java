package com.example.paymentgateway.handler.clients;

import com.example.paymentgateway.error.PaymentGatewayException;
import com.example.paymentgateway.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientInterfaceImpl implements ClientInterface {

    private final ClientsHandler clientsHandler;

    /**
     * Adds the given client to existing clients list
     * @param client Client to be added
     */
    @Override
    public void addClient(final Client client) {
        log.info("Adding client {} to payment gateway", client.getName().toUpperCase());
        if (clientsHandler.getSupportClients().containsKey(client.getName().toUpperCase())) {
            log.warn("Client is registered on PG already. Returning");
            return;
        }

        clientsHandler.addClient(client.getName().toUpperCase(), client);
    }

    /**
     * Removes the given client from the existing clients list
     * @param client Client to be removed
     */
    @Override
    public void removeClient(final Client client) {
        log.info("Removing client {} from payment gateway", client.getName().toUpperCase());
        if (!clientsHandler.getSupportClients().containsKey(client.getName().toUpperCase())) {
            throw new PaymentGatewayException("Client Not found", HttpStatus.NOT_FOUND, null)
                    .logError(log);
        }

        clientsHandler.removeClient(client.getName().toUpperCase());
    }

    /**
     * Checks if given client exists in the list of existing clients
     * @param client Client to be verified if exists
     */
    @Override
    public boolean hasClient(final Client client) {
        if (clientsHandler.getSupportClients().containsKey(client.getName().toUpperCase())) {
            log.info("Client {} found in the Payment Gateway", client.getName().toUpperCase());
            return true;
        } else {
            log.info("Client {} could not be found in the Payment Gateway", client.getName().toUpperCase());
            return false;
        }
    }


}
