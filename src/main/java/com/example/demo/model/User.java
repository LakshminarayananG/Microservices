package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype") // This annotation will help to create multiple objects instead of following singleton
public class User {

    private int id;
    private String name;
    /*@JsonProperty("first_name")
    private String firstName;*//*@JsonProperty("first_name")
    private String firstName;*/
    private LocalDate dob;

    public User() {
        System.out.println("User Object created");
    }

}
