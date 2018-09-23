package jp.co.sparkworks.restaurant.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("jp.co.sparkworks.restaurant")
public class BackOfficeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BackOfficeApplication.class, args);
	}
}
