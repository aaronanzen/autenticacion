package mx.com.beo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class AutenticacionApp {

	public static void main(String[] args) {
		SpringApplication.run(AutenticacionApp.class, args);
	}
}
