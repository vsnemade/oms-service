package com.vishtech.oms.service;

import com.vishtech.oms.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile({"prod","qa"})
public class EmailNotificationService implements NotificationService{

    @Autowired
    private AppProperties appProperties;
    @Override
    public void notifyUser(String message) {
        log.info(appProperties.getEnvironment()+ ": "+message);
    }
}
