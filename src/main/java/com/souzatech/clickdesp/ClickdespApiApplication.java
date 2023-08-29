package com.souzatech.clickdesp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ClickdespApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickdespApiApplication.class, args);
	}

}
