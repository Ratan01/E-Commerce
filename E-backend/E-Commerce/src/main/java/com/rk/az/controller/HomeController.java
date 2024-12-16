package com.rk.az.controller;

import com.rk.az.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping()
    public ApiResponse homeControllerReturn(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMesssage("Welcome To E-commerce Application");
        return apiResponse;
    }
}
