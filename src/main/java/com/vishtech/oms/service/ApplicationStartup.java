package com.vishtech.oms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ApplicationStartup implements ApplicationRunner {

    @Autowired
    private NotificationService notificationService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        notificationService.notifyUser("Application Started");
    }
}
