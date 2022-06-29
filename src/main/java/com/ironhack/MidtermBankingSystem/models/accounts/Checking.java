package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
    })
    private Money minimumBalance;

    private BigDecimal monthlyMaintenanceFee;

    /**
     * Empty constructor
     */
    public Checking() {

    }

    /**
     * Checking Account Constructor with one owner, specifying balance, secret key, primary owner and penalty fee,
     * status, creation date, minimum balance and monthly maintenance fee. If status is null, is assigned by default
     * Status.ACTIVE. If creation date is null, is assigned by default the current date.
     *
     * When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account will be
     * created otherwise a regular Checking Account will be created.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     * @param minimumBalance
     * @param monthlyMaintenanceFee
     */
    public Checking(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee, Status status, LocalDate creationDate, Money minimumBalance, BigDecimal monthlyMaintenanceFee) {
        super(balance, secretKey, primaryOwner, penaltyFee, status, creationDate);
        if (Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() < 24) {
            System.out.println("A StudentChecking account should be created, this" +
                    "account does not have minimum balance neither monthly maintenance fee");
        } else {
            this.minimumBalance = minimumBalance;
            this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        }
    }

    /**
     * Checking Account Constructor with one owner, specifying balance, secret key, primary owner and penalty fee,
     * status, creation date, minimum balance and monthly maintenance fee. If status is null, is assigned by default Status.ACTIVE. If creation date is null,
     * is assigned by default the current date.
     *
     * When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account will be
     * created otherwise a regular Checking Account will be created.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     * @param minimumBalance
     * @param monthlyMaintenanceFee
     */
    public Checking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, Status status, LocalDate creationDate, Money minimumBalance, BigDecimal monthlyMaintenanceFee) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status, creationDate);
        if (Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() < 24) {
            System.out.println("A StudentChecking account should be created, this" +
                    "account does not have minimum balance neither monthly maintenance fee");
        } else {
            this.minimumBalance = minimumBalance;
            this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        }
    }



    /*
    Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12*/
}
