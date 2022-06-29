package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.auxiliary.Utilities;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "account_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    @Column(unique = true)
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency")),
    })
    private Money balance;

    private String secretKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "primary_owner")
    private AccountHolder primaryOwner;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "secondary_owner")
    private AccountHolder secondaryOwner; // Un constructor con y otro sin


    private BigDecimal penaltyFee;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate creationDate;

    /**
     * Empty constructor
     */
    public Account() {
    }

    /**
     * Constructor with one owner, specifying balance, secret key, primary owner and penalty fee,
     * status default as active and creation date default as current date.
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param penaltyFee
     */
    public Account(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = penaltyFee;
        this.status= Status.ACTIVE;
        this.creationDate = LocalDate.now();
    }

    /**
     *  Constructor with one owner, specifying balance, secret key, primary owner and penalty fee, status
     *  and creation date.
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     */
    public Account(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee,
                   Status status, LocalDate creationDate) {
        this(balance, secretKey, primaryOwner, penaltyFee);
        this.status = status;
        this.creationDate = creationDate;
    }

    /**
     * Constructor with two owner, specifying balance, secret key, primary owner, secondary owner and penalty
     * fee, status default as active and creation date default as current date.
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param penaltyFee
     */
    public Account(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                   BigDecimal penaltyFee) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.status= Status.ACTIVE;
        this.creationDate = LocalDate.now();
    }

    /**
     * Constructor with two owner, specifying balance, secret key, primary owner, secondary owner and penalty
     * fee, status and creation date.
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     */
    public Account(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                   BigDecimal penaltyFee, Status status, LocalDate creationDate) {
        this (balance, secretKey, primaryOwner, secondaryOwner, penaltyFee);
        this.status = status;
        this.creationDate =creationDate;

    }

    // Getter and Setter ID
    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = Utilities.randomId();
    }

    // Getter and Setter Balance
    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    // Getter and Setter SecretKey
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    // Getter and Setter Primary Owner
    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    // Getter and Setter Secondary Owner
    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    // Getter and Setter Penalty Fee
    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    // Getter and Setter Status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Getter and Setter Creation Date
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
