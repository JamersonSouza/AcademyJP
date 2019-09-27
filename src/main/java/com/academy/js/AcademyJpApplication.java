package com.academy.js;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.academy.controller.AlunoController;

@SpringBootApplication
@ComponentScan(basePackages = "com.academy.controller")
public class AcademyJpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyJpApplication.class, args);
	}

}
