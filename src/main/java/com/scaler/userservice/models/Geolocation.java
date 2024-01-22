package com.scaler.userservice.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Geolocation{
     private double latitude;
     private double longitude;
}
