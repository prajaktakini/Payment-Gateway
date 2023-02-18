package com.example.paymentgateway.handler.paymodes;

import com.example.paymentgateway.model.Client;
import com.example.paymentgateway.model.PayMode;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface PayModesInterface {

    public @NonNull Set<PayMode> listSupportedPayModes();

    public @NonNull Set<PayMode> listSupportedPayModes(@NonNull Client client);

    public void addSupportForPayMode(PayMode payMode);

    public void addSupportForPayMode(PayMode payMode, Client client);

    public void removePayMode(PayMode payMode);


}
