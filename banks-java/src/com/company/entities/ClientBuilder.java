package com.company.entities;

public class ClientBuilder {
    Client client;

    ClientBuilder() {
        reset();
    }

    void reset() {
        client = new Client();
    }

    void setFullName(String fullName) {
        client.setFullName(fullName);
    }

    void setAddress(String address) {
        client.setAddress(address);
    }

    void setPassport(String passport) {
        client.setPassport(passport);
    }

    Client getClient() {
        Client client = this.client;
        reset();
        return client;
    }
}
