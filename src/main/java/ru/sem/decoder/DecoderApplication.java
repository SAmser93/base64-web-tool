package ru.sem.decoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class DecoderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
	    SpringApplication.run(DecoderApplication.class, args);
	}

}

