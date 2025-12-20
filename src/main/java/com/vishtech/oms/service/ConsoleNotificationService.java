package com.vishtech.oms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dev")
public class ConsoleNotificationService implements NotificationService{
    @Override
    public void notifyUser(String message) {
      log.info("DEV: "+message);
    }
}
