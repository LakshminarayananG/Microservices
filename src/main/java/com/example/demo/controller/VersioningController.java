package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class VersioningController {

    @GetMapping(path = "/users/v1",produces = "application/json")
    public User getV1User() {
    return User.builder().build();
    }


    @GetMapping(path = "/users/v2",produces = "application/json")
    public UserV2 getV2User(){
        UserV2.Name name = UserV2.Name.builder()
                .firstName("Lakshmi")
                .lastName("Ganesan")
                .build();
        UserV2.Address address = UserV2.Address.builder().street("Second Cross Street").city("Chennai").country("India").pin("600042").build();
        return UserV2.builder().id(1)
                .name(name)
                .dob(LocalDate.of(2000,12,20))
                .address(address).build();
    }
}
