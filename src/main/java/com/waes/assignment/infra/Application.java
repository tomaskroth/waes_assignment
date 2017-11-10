package com.waes.assignment.infra;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

/**
 * <p>
 * This is the main class of the application responsible for handling Spring initialization
 * </p>
 *
 * @author Tomás Kroth
 * @since 11/10/2017
 */
@SpringBootApplication(scanBasePackages = "com.waes.assignment")
public class Application {

    //Using literals here because this is the only situation that we'll have a static method using a logger.
    private static final Logger logger = Logger.getLogger("Application");

    /**
     * This method actually starts the application
     *
     * @param args Default args for SpringInitialization
     */
    public static void main(final String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(Application.class, args);
        logger.info("Application initialized");
    }
}
