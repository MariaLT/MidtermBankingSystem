package com.ironhack.MidtermBankingSystem.controller.impl.accounts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createSavingAccount() {
    }

    @Test
    void createCheckingAccount() {
    }

    @Test
    void createCreditCardAccount() {
    }

    @Test
    void updateStatusAccount() {
    }

    @Test
    void applyInterestSavingAccount() {
    }

    @Test
    void applyInterestCreditCard() {
    }

    @Test
    void monthlyMaintenanceFee() {
    }

    @Test
    void penaltyFee() {
    }

    @Test
    void modifyBalanceByAdmin() {
    }

    @Test
    void accountInfoToAdminById() {
    }

    @Test
    void accountInfoToAdmin() {
    }

    @Test
    void accountBalanceOwner() {
    }

    @Test
    void transfer() {
    }
}