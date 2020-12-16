package com.daddysCoffee.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class Application {

	//Main method of the application
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
