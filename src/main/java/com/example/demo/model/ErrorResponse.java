package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor



/*
This class is to just print the necessary details in the response

 */
public class ErrorResponse {


    private int code;
    private List<String> message;
    private String details;

    private LocalDateTime timeStamp;



}
