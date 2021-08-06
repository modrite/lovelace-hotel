package com.group3.lovelacehotel;

import com.group3.lovelacehotel.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LovelaceHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovelaceHotelApplication.class, args);
	}

}
