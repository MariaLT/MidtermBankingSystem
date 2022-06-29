package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
    })
    private Money creditLimit;

    private BigDecimal interestRate;


}
