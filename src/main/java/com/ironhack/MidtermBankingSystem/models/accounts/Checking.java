package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.interfaces.Penalty;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    /**
     *  Minimum balance:
     *  250
     */
    @DecimalMin(value = "250", message = "The balance should be as minimal 250")
    public final static BigDecimal minimumBalance = BigDecimal.valueOf(250);

    /**
     * Minimum monthly maintenance fee:
     * 12
     */
    @DecimalMin(value = "12", message = "The monthly maintenance fee should be as minimal 12")
    private final static BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);

    /**
     * Empty constructor
     */
    public Checking() {

    }

    /**
     * Constructor with two owners, specifying balance, secret key, primary owner, optional secondary owner, status and
     * creation date. If status is null, is assigned by default Status.ACTIVE. Creation date is assigned
     * by default the current date.
     *
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     */
    public Checking(Long id, Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                    Status status, LocalDate creationDate) {
        super(id, balance, secretKey, primaryOwner, secondaryOwner, status, creationDate);
    }

/*
    *//**
     * Checking accounts should have a minimum balance, if the balance if less than the minimum,
     * the value for the balance
     * @param balance
     *//*
    @Override
    public void setBalance(Money balance) {
        if (balance.getAmount().compareTo(minimumBalance)==-1){
            super.setBalance(null);
        } else {
            super.setBalance(balance);
        }

    }*/


}
