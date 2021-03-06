package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {

    /**
     * Empty constructor
     */
    public StudentChecking() {
    }

    /**
     * Constructor with two owners, specifying balance, secret key, primary owner, optional secondary owner, status and
     * creation date. If status is null, is assigned by default Status.ACTIVE. Creation date is assigned
     * by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     */
    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                           Status status, LocalDate creationDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, creationDate);
    }


}
