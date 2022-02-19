package com.company.entities;

public class Client {
    private String fullName;
    private String address;
    private String passport;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean isSuspicious()
    {
        return address == null || passport == null || address.isEmpty() || passport.isEmpty();
    }
}
