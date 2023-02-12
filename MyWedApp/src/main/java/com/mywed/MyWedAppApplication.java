package com.mywed;

import com.mywed.repository.LoginRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class MyWedAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWedAppApplication.class, args);
	}

}
