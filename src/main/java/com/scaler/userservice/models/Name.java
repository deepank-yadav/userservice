package com.scaler.userservice.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Name {
    private String firstName;
    private String lastName;
}
