package com.scaler.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address{
    private String street;
    private String city;
    private String zipcode;
//    @Embedded
//    private Geolocation geolocation;
}
