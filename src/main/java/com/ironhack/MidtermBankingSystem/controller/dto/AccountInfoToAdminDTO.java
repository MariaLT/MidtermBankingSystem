package com.ironhack.MidtermBankingSystem.controller.dto;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.List;

public class AccountInfoToAdminDTO {




    List<String> namesOwners;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
    })
    private Money balance;

    public AccountInfoToAdminDTO(List<String> namesOwners, Money balance) {
        this.namesOwners = namesOwners;
        this.balance = balance;
    }

    public List<String> getNamesOwners() {
        return namesOwners;
    }

    public void setNamesOwners(List<String> namesOwners) {
        this.namesOwners = namesOwners;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
