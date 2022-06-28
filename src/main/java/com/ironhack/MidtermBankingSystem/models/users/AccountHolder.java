package com.ironhack.MidtermBankingSystem.models.users;


import com.ironhack.MidtermBankingSystem.auxiliary.Address;

import java.time.LocalDate;

/**
 * Represent a user who is able to access their own accounts and only their accounts when passing
 * the correct credentials using Basic Auth.
 */
public class AccountHolder {

    private String name;
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private String mailingAddress; // regex



}
