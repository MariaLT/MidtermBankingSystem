package com.ironhack.MidtermBankingSystem.repository.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.Saving;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavingRepositoryTest {

    @Autowired
    SavingRepository savingRepository;

    Address address;

//    Address address = new Address();

    AccountHolder accountHolder = new AccountHolder(1L, "OWNER_1",
            "$2a$10$dnMdFNuM6R6.0QNMROCfYOlAHU.eM4kqw.04PEbLP7E.qjI3wPbsq",
            "Sofía", LocalDate.of(1991, 07, 22), address, "sofia@sofia.com");
    Saving saving = new Saving(4l, new Money(BigDecimal.valueOf(2000)), "romero", accountHolder, Status.ACTIVE, null,
            BigDecimal.valueOf(0.0025));

    /*   public Address(String street, short number, byte floor, String door, int zipCode,
                   String city, String country)*/
    @BeforeEach
    void setUp() {
        address = new Address("Calle", 2, 2, "E", 15263, "Madrid", "España");
    }

    @AfterEach
    void tearDown() {
    }


}