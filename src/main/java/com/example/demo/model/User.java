package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
@Component
@Scope("prototype") // This annotation will help to create multiple objects instead of following singleton
public class User {


    /*
    The validations are added below.

    @NotNull => To ensure this value is not given as null
    @Size => To ensure the size of the name is between 2 to 10
     */

    //@NotNull(message="ID can't be null")
    @JsonIgnore
    private int id;
    @NotNull(message="Name is required ba")
    @Size(min=2, max=10, message="Name should between 2 and 10 in size")


    private String name;



    private LocalDate dob;

    public User() {
        System.out.println("User Object created");
    }

}
