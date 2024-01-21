package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Address  extends BaseModel{
    private String street;
    private String city;
    private String pincode;
    @OneToOne
    private Geolocation geolocation;
}
