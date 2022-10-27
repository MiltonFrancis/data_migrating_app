package com.fullstackit.data_migrating_app.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CombinedController {



    @GetMapping(value = "/")
    public String getResponse(){
        return "";
    }
}