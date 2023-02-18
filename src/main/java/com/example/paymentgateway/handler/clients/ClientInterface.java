package com.example.paymentgateway.handler.clients;

import com.example.paymentgateway.model.Client;
import org.springframework.lang.NonNull;

public interface ClientInterface {

    public void addClient(@NonNull Client client);

    public void removeClient(@NonNull Client client);

    public boolean hasClient(@NonNull Client client);
}
