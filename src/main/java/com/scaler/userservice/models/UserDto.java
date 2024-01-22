package com.scaler.userservice.models;

import com.scaler.userservice.models.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserDto extends BaseModel {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String name;
    @OneToOne
    private Address address;
    private String phone;

}
