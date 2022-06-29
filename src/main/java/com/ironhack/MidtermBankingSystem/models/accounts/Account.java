package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.auxiliary.Utilities;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    @Column(unique = true)
    private Long id;
    @Embedded
    private Money balance;

    private String secretKey;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "primary_owner")
    private AccountHolder primaryOwner;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "secondary_owner")
    private AccountHolder secondaryOwner; // Un constructor con y otro sin

    @Embedded
    private Money penaltyFee;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate creationDate;


    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = Utilities.randomId();
    }
}
