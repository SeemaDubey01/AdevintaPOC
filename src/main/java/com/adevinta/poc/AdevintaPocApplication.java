package com.adevinta.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AdevintaPocApplication  extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder  configure(SpringApplicationBuilder application) {
         return application.sources(AdevintaPocApplication .class);
    }

	public static void main(String[] args) {
		SpringApplication.run(AdevintaPocApplication.class, args);
	}

}

