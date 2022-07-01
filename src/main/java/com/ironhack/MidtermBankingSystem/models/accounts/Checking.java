package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.interfaces.Penalty;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account implements Penalty {


    @DecimalMin(value = "250", message = "The balance should be as minimal 250")
    private final static BigDecimal minimumBalance = BigDecimal.valueOf(250);

    @DecimalMin(value = "12", message = "The monthly maintenance fee should be as minimal 12")
    private final static BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);

    /**
     * Empty constructor
     */
    public Checking() {

    }

    /**
     * Checking Account Constructor with one owner, specifying balance, secret key, primary owner,
     * status, creation date. If status is null, is assigned by default
     * Status.ACTIVE. Creation date is assigned by default the current date.
     *
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param status
     */
    public Checking(Long id, Money balance, String secretKey, AccountHolder primaryOwner, Status status, LocalDate creationDate) {
        super(id, balance, secretKey, primaryOwner, status, creationDate);
    }

    /**
     * Checking Account Constructor with one owner, specifying balance, secret key, primary owner,
     * status, creation date. If status is null, is assigned by default
     * Status.ACTIVE. Creation date is assigned by default the current date.
     *
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     */
    public Checking(Long id, Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                    Status status, LocalDate creationDate) {
        super(id, balance, secretKey, primaryOwner, secondaryOwner, status, creationDate);
    }



    @Override
    public void setBalance(Money balance) {
        if (balance.getAmount().compareTo(minimumBalance)==-1){
            super.setBalance(null);
        } else {
            super.setBalance(balance);
        }

    }

    /**
     * Deduce a penalty fee from the balance, when the account drop below the minimum balance.
     */
    public void penaltyFee(){
        if (getBalance().getAmount().compareTo(minimumBalance)==-1){
            getBalance().decreaseAmount(PENALTY_FEE);
        }
    }
}
