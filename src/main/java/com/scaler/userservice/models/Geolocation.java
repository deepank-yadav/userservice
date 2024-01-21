package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Geolocation extends BaseModel{
     long latitude;
     long longitude;
}
