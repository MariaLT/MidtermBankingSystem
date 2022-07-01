package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.interfaces.Interest;
import com.ironhack.MidtermBankingSystem.interfaces.Penalty;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account implements Interest, Penalty {



    private BigDecimal interestRate;

    private LocalDate interestAddDate;

    /**
     * Minimum default value of interest rate:
     * 0.0025
     */
    private final static BigDecimal DEFAULT_MIN_VALUE_INTEREST_RATE = BigDecimal.valueOf(0.0025);


    /**
     * Maximum value of interest rate:
     * 0.50
     */
    private final static BigDecimal MAX_VALUE_INTEREST_RATE = BigDecimal.valueOf(0.50);




    /**
     * Minimum default value of balance:
     * 1000
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
    })
    private final static Money DEFAULT_MIN_BALANCE = new Money( BigDecimal.valueOf(1000));


    /**
     * Minimum value of balance:
     * 1000
     */
    @DecimalMin(value = "100", message = "It may be instantiated with a minimum balance of less " +
            "than 1000 but no lower than 100" )
    private final static BigDecimal MIN_BALANCE = BigDecimal.valueOf(100);


    /**
     * Empty constructor
     */
    public Saving() {

    }


    /**
     * Saving Account Constructor with one owner, specifying balance, secret key, primary owner,
     * status, creation date and interest rate. If status is null, is assigned by default Status.ACTIVE.
     * Creation date is assigned by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param status
     * @param interestRate
     */
    public Saving(Long id, Money balance, String secretKey, AccountHolder primaryOwner,
                  Status status, LocalDate creationDate, BigDecimal interestRate) {
        super(id, balance, secretKey, primaryOwner, status, creationDate);
        setBalance(balance);
        setInterestRate(interestRate);
        this.interestAddDate = null;
    }


    /**
     * Saving Account Constructor with two owners, specifying balance, secret key, primary owner, secondary owner
     * and penalty fee, status, creation date and interest rate. If status is null, is assigned
     * by default Status.ACTIVE. Creation date is assigned by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     * @param interestRate
     */
    public Saving(Long id,Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                  Status status, LocalDate creationDate, BigDecimal interestRate) {
        super(id,balance, secretKey, primaryOwner, secondaryOwner, status, creationDate);
        setBalance(balance);
        setInterestRate(interestRate);
        this.interestAddDate = null;
    }

    // Getter and Setter Interest Rate
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * Savings accounts have a default interest rate of 0.0025. It may be instantiated with an interest rate other than
     * the default, with a maximum interest rate of 0.5.
     * @param interestRate
     */
    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(DEFAULT_MIN_VALUE_INTEREST_RATE) == -1) {
            this.interestRate = DEFAULT_MIN_VALUE_INTEREST_RATE;
        } else if (interestRate.compareTo(MAX_VALUE_INTEREST_RATE) == 1) {
            this.interestRate = MAX_VALUE_INTEREST_RATE;

        } else {
            this.interestRate = interestRate;
        }
    }

    // Getter and Setter Interest Add Date
    public LocalDate getInterestAddDate() {
        return interestAddDate;
    }

    public void setInterestAddDate(LocalDate interestAddDate) {
        this.interestAddDate = interestAddDate;
    }

    /**
     * Deduce a penalty fee from the balance, when the account drop below the minimum balance.
     */
    public void penaltyFee(){
        if (getBalance().getAmount().compareTo(MIN_BALANCE)==-1){
            getBalance().decreaseAmount(PENALTY_FEE);
        }
    }

    /**
     * Added to the account annually at the rate of specified interestRate per year.
     */
    public void interest(){
        Money money;
        if (Period.between(getCreationDate(), LocalDate.now()).getYears() == 1){

            setBalance(new Money(getBalance().increaseAmount(interestRate.multiply(getBalance().getAmount()))));
            setInterestAddDate(LocalDate.now());
        } else if (Period.between(getInterestAddDate(), LocalDate.now()).getYears() == 1) {
            setBalance(new Money(getBalance().increaseAmount(interestRate.multiply(getBalance().getAmount()))));
            setInterestAddDate(LocalDate.now());
        }
    }

    /*    public static void interest(Saving saving){
        if (Period.between(saving.getCreationDate(), LocalDate.now()).getYears() == 1){
            saving.getBalance().increaseAmount(saving.getInterestRate().multiply(saving.getBalance().
                    getAmount()));
            saving.setInterestAddDate(LocalDate.now());
        } else if (Period.between(saving.getInterestAddDate(), LocalDate.now()).getYears() == 1) {
            saving.getBalance().increaseAmount(saving.interestRate.multiply(saving.getBalance().getAmount()));
            saving.setInterestAddDate(LocalDate.now());
        }
    }*/
    @Override
    public void setBalance(Money balance) {
        if (balance.getAmount().compareTo(MIN_BALANCE)==-1){
            super.setBalance(null);
        } else {
            super.setBalance(balance);
        }

    }
}
