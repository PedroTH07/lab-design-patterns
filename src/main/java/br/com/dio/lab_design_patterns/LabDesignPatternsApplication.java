package br.com.dio.lab_design_patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LabDesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabDesignPatternsApplication.class, args);
	}

}
