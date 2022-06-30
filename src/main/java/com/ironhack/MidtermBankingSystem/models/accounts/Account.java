package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.service.UtilityService;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private AccountHolder secondaryOwner;

    private static UtilityService utilityService;

    /**
     * ¿Por qué no me deja?
     * {@value BigDecimal.valueOf(40)}
     */
    public static final BigDecimal PENALTY_FEE = BigDecimal.valueOf(40);

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate creationDate;

    /**
     * Empty constructor
     */
    public Account() {
    }

    /**
     * Constructor with one owner, specifying balance, secret key, primary owner, status
     * and creation date. If status is null, is assigned by default Status.ACTIVE. Creation date is assigned
     * by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param status
     */
    public Account(Money balance, String secretKey, AccountHolder primaryOwner,
                   Status status) {
        this.id = utilityService.randomId();
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        setStatus(status);
        this.creationDate = LocalDate.now();
    }


    /**
     * Constructor with two owners, specifying balance, secret key, primary owner, secondary owner, status and
     * creation date. If status is null, is assigned by default Status.ACTIVE. Creation date is assigned
     * by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     */
    public Account(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                   Status status) {
        this.id = utilityService.randomId();
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        setStatus(status);
        this.creationDate = LocalDate.now();
    }

    // Getter and Setter ID
    public Long getId() {
        return id;
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


    // Getter and Setter Status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            this.status = Status.ACTIVE;
        } else {
            this.status = status;
        }
    }

    // Getter and Setter Creation Date
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


}
