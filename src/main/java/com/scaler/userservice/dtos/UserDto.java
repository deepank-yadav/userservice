package com.scaler.userservice.dtos;

import com.scaler.userservice.models.BaseModel;
import jakarta.persistence.Entity;
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
    private String address;
    private String phone;

}
