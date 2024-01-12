package com.gautami.shipit.config;

import com.gautami.shipit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner, ApplicationInitializer {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        initialize();
    }

    @Override
    public void initialize() {
        userService.createAdminUser();
    }
}

