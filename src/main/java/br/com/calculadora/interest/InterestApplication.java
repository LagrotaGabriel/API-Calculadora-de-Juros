package br.com.calculadora.interest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.calculadora.interest.models"})
@EnableJpaRepositories("br.com.calculadora.interest.repositories")
@ComponentScan("br.com.calculadora.interest.resources")
@ComponentScan("br.com.calculadora.interest.services")
@ComponentScan("br.com.calculadora.interest.exceptions")
@ComponentScan("br.com.calculadora.interest.config")
public class InterestApplication {
	public static void main(String[] args) {
		SpringApplication.run(InterestApplication.class, args);
	}
}
