package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype") // This annotation will help to create multiple objects instead of following singleton

public class UserV2 {

    private int id;
    private Name name;

    private LocalDate dob;

    private Address address;


    @AllArgsConstructor
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    public static class Name {
        private String firstName;
        private String lastName;
    }

    @AllArgsConstructor
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    public static class Address {
        private String street;
        private String city;

        private String country;

        private String pin;
    }



}
