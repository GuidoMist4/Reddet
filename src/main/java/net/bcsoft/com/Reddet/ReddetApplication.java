package net.bcsoft.com.Reddet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ReddetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReddetApplication.class, args);
	}

}
