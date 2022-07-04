package com.ironhack.MidtermBankingSystem.service.impl.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.User;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
import com.ironhack.MidtermBankingSystem.repository.users.UserRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.AccountHolderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountHolderServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountHolderService accountHolderService;

    private AccountHolder accountHolder1;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "number", column = @Column(name = "building_number")),
            @AttributeOverride(name = "floor", column = @Column(name = "floor")),
            @AttributeOverride(name = "door", column = @Column(name = "door")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private Address address;

    private Account account;

    private LocalDate birthdate;

    @BeforeEach
    void setUp() {


        birthdate = LocalDate.of(1999, 07, 22);
        accountHolder1 = new AccountHolder("owner_1", "sandia", "Paloma", LocalDate.of(1999,02,02),
                address, "paloma@paloma.es");

    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void createAccountHolder()  {

        accountHolder1.setId(0L);
        accountHolderService.createAccountHolder(accountHolder1);

        assertEquals(accountHolder1.getName(),  accountHolderService.createAccountHolder(accountHolder1).getName());

    }


}