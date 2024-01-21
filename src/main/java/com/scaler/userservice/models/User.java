package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User extends BaseModel{

    private String email;
    private String username;
    private String password;
    private String name;
    @OneToOne
    private Address address;
    private String phone;

}
