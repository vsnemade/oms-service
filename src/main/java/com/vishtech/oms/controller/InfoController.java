package com.vishtech.oms.controller;


import com.vishtech.oms.config.AppProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private final AppProperties appProperties;

    public InfoController(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @GetMapping("/env")
    public String environment() {
        return "Running in " + appProperties.getEnvironment() + " environment";
    }
}
