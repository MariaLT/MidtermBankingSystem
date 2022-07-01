package com.ironhack.MidtermBankingSystem;

import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.Saving;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class MidtermBankingSystemApplication {

	@Autowired
	UtilityService utilityService;
	public static void main(String[] args) {

		SpringApplication.run(MidtermBankingSystemApplication.class, args);
	}



/*	Address address = new Address();

	AccountHolder accountHolder = new AccountHolder(1L, "OWNER_1",
			"$2a$10$dnMdFNuM6R6.0QNMROCfYOlAHU.eM4kqw.04PEbLP7E.qjI3wPbsq",
			"Sofía", LocalDate.of(1991, 07, 22), address, "sofia@sofia.com");
	Saving saving = new Saving(new Money(BigDecimal.valueOf(2000)), "romero", accountHolder, Status.ACTIVE,
			BigDecimal.valueOf(0.0025));*/



	



}

