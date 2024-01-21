package com.scaler.userservice.models;

import jakarta.persistence.OneToOne;

public class Address {

    private String street;
    private String city;
    private String pincode;

    @OneToOne
    private Geolocation geolocation;
}
