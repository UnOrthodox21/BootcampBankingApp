package com.bootcampTeam4.bootcampBankingApp;

import com.bootcampTeam4.bootcampBankingApp.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BootcampBankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcampBankingAppApplication.class, args);
	}

}
