package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.auxiliary.Utilities;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @Column(unique = true)
    private Long id;
    private Money balance;
    private String secretKey;
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner; // Un constructor con y sin
    private Money penaltyFee;
    private Status status;
    private LocalDate creationDate;


    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = Utilities.randomId();
    }
}
