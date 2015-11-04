package com.tjazi.profilesauthorizer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Krzysztof Wasiak on 04/11/2015.
 */

@ComponentScan
@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }
}
