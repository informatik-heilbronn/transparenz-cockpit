package de.hhn.seb.labsw.transparentcockpit.backend.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Spring Boot Application Service.
 */
public class BackendApplicationService {

    private static ConfigurableApplicationContext context;

    public static void run() {
        SpringApplication app = new SpringApplication(BackendApplication.class);
        context = app.run("");
    }

    public static void exit() {
        SpringApplication.exit(context, () -> 0);
    }
}
