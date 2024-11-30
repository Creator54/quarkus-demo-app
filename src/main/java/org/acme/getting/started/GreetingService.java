package org.acme.getting.started;

import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GreetingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    public String greeting(String name) {
        String message = "hello " + name;
        LOGGER.info("Generating greeting: {}", message);
        return message;
    }
}
