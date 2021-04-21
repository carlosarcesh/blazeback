package com.carlosarce.blaze.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String birthDate;
}
