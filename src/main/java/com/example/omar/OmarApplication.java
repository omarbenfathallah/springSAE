package com.example.omar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OmarApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmarApplication.class, args);
        System.out.println("JAWEK FESFES");
    }

}
