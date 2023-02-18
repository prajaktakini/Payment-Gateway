package com.example.paymentgateway.handler.clients;

import com.example.paymentgateway.model.Client;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ClientsHandler {

    private Map<String, Client> clientsMap;

    public Map<String, Client> getSupportClients() {
        return clientsMap;
    }

    public void addClient(@NonNull String name, @NonNull Client client) {
        clientsMap.put(name.toUpperCase(), client);
    }

    public void removeClient(@NonNull String name) {
        clientsMap.remove(name.toUpperCase());
    }
}
