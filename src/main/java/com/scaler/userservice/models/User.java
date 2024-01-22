package com.scaler.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{

    private String email;
    private String username;
    @JsonIgnore
    private String password;
    @Embedded
    private Name name;
    @Embedded
    private Address address;
    private String phone;

}
