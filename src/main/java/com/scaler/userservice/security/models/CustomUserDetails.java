package com.scaler.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import com.scaler.userservice.security.services.CustomGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
public class CustomUserDetails implements UserDetails {

    private User user;
    private List<CustomGrantedAuthority> authorities;
    private String password;

    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


    private Long userId;

    public CustomUserDetails(){
    }
    public CustomUserDetails(User user) {
        this.user = user;
        this.username = user.getEmail();
        this.password = user.getHashPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;
        this.credentialsNonExpired = true;
        List<CustomGrantedAuthority> grantedAuthorities =  new ArrayList<>();

        for(Role role: user.getRole()){
            grantedAuthorities.add(new CustomGrantedAuthority(role));
        }
        this.authorities = grantedAuthorities;
        this.userId = user.getId();

    }
    public Long getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
