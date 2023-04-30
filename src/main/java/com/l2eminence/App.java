package com.l2eminence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        DbMessenger messenger = new DbMessenger();
        SpringApplication.run(App.class, args);
    }
}
