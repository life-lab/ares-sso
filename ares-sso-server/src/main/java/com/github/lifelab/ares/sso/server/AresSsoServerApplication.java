package com.github.lifelab.ares.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author xuxueli 2018-03-22 23:41:47
 */
@SpringBootApplication
public class AresSsoServerApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(AresSsoServerApplication.class, args);
    }
}