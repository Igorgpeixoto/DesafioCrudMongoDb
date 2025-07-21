package Desafio.Crud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.annotation.ApplicationScope;

@SpringBootApplication
@ApplicationScope
public class DesafioCrudMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCrudMongoDbApplication.class, args);
	}

}
