package com.scaler.userservice.security.services;

import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.UserRepository;
import com.scaler.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findAllByEmail(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User with email " + username + " not found");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user.get());
        return userDetails;
    }
}
