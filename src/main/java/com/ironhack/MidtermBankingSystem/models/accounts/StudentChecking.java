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
    public StudentChecking() {
    }

    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee, Status status, LocalDate creationDate) {
        super(balance, secretKey, primaryOwner, penaltyFee, status, creationDate);
    }

    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, Status status, LocalDate creationDate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status, creationDate);
    }
}
