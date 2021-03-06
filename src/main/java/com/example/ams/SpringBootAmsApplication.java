package com.example.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class SpringBootAmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmsApplication.class, args);
    }

    @PostConstruct
    private void init(){
        String timezone = TimeZone.getDefault().getID();
        System.out.println(timezone);
        System.setProperty("spring.jackson.time-zone",timezone);
    }
}
