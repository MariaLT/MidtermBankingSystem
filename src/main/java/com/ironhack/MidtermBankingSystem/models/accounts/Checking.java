package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;

import javax.persistence.*;
import java.math.BigDecimal;

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


}
