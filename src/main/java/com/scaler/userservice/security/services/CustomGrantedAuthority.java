package com.scaler.userservice.security.services;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;
@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

//    private Role role;
    private String authority;
    public CustomGrantedAuthority(){
    }

    public CustomGrantedAuthority(Role role){
        this.authority = role.getName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
