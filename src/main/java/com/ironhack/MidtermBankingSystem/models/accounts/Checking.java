package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    /**
     *  Minimum balance:
     *  250
     */
    @DecimalMin(value = "250", message = "The balance should be as minimal 250")
    public final static BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(250);

    /**
     * Minimum monthly maintenance fee:
     * 12
     */
    @DecimalMin(value = "12", message = "The monthly maintenance fee should be as minimal 12")
    public final static BigDecimal MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(12);

    private LocalDate dateMaintenanceFee;

    /**
     * Empty constructor
     */
    public Checking() {

    }

    /**
     * Constructor with two owners, specifying balance, secret key, primary owner, optional secondary owner, status and
     * creation date. If status is null, is assigned by default Status.ACTIVE. Creation date is assigned
     * by default the current date.
     *
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     */
    public Checking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                    Status status, LocalDate creationDate, LocalDate dateMaintenanceFee) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status, creationDate);
        this.dateMaintenanceFee = dateMaintenanceFee;
    }

    public LocalDate getDateMaintenanceFee() {
        return dateMaintenanceFee;
    }

    public void setDateMaintenanceFee(LocalDate dateMaintenanceFee) {
        this.dateMaintenanceFee = dateMaintenanceFee;
    }
}
