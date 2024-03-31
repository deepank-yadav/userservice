package com.scaler.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{

    private String email;
    private String username;
    @ManyToMany
    private List<Role> role;
    @JsonIgnore
    private String hashPassword;
    //@Embedded
    private String name;
    @Embedded
    private Address address;
    private String phone;


}
