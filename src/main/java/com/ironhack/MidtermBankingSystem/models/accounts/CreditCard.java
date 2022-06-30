package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.interfaces.Interest;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account implements Interest {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
    })
    private Money creditLimit;

    private BigDecimal interestRate;

    private LocalDate interestAddDate;

    /**
     * Default value of credit limit:
     * 100
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
    })
    private final static Money DEFAULT_CREDIT_LIMIT = new Money(BigDecimal.valueOf(100));


    /**
     * Maximum value of credit limit:
     * 100
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
    })
    private final static Money MAXIMUM_CREDIT_LIMIT = new Money(BigDecimal.valueOf(100000));


    /**
     * Default value of interest rate:
     * 0.2
     */
    private final static BigDecimal DEFAULT_VALUE_INTEREST_RATE = BigDecimal.valueOf(0.2);


    /**
     * Minimum value of interest rate:
     * 0.1
     */
    private final static BigDecimal MIN_VALUE_INTEREST_RATE = BigDecimal.valueOf(0.1);




    /**
     * Empty constructor
     */
    public CreditCard() {

    }

    /**
     * CreditCard Account Constructor with one owner, specifying balance, secret key, primary owner,
     * status, creation date, credit limit and interest rate. If status is null, is assigned by default Status.ACTIVE.
     * Creation date is assigned by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param status
     * @param creditLimit
     * @param interestRate
     */
    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, Status status,
                      Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    /**
     * CreditCard Account Constructor with two owners, specifying balance, secret key, primary owner and penalty fee,
     * status, creation date, credit limit and interest rate. If status is null, is assigned by default Status.ACTIVE.
     *Creation date is assigned by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param status
     * @param creditLimit
     * @param interestRate
     */
    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                      Status status, Money creditLimit,
                      BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }


    // Getter and Setter creditLimit
    public Money getCreditLimit() {
        return creditLimit;
    }

    /**
     * CreditCard accounts have a default creditLimit of 100. It may be instantiated with a creditLimit higher than 100
     * but not higher than 100000.
     * @param creditLimit
     */
    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(DEFAULT_CREDIT_LIMIT.getAmount()) == 1 &&
                creditLimit.getAmount().compareTo(MAXIMUM_CREDIT_LIMIT.getAmount()) == -1) {
            this.creditLimit = creditLimit;
        } else {
            this.creditLimit = DEFAULT_CREDIT_LIMIT;
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
     * CreditCards have a default interestRate of 0.2. It may be instantiated with an interestRate less than 0.2
     * but not lower than 0.1.
     * @param interestRate
     */
    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(MIN_VALUE_INTEREST_RATE) == 1 &&
                interestRate.compareTo(DEFAULT_VALUE_INTEREST_RATE) == -1) {
            this.interestRate = interestRate;
        } else {
            this.interestRate=DEFAULT_VALUE_INTEREST_RATE;
        }

    }

    @Override
    public void interest() {
        if (Period.between(getCreationDate(), LocalDate.now()).getMonths() == 1){
            getBalance().increaseAmount(interestRate.divide(BigDecimal.valueOf(12)).multiply(getBalance().getAmount()));
            setInterestAddDate(LocalDate.now());
        } else if (Period.between(getInterestAddDate(), LocalDate.now()).getYears() == 1) {
            getBalance().increaseAmount(interestRate.divide(BigDecimal.valueOf(12)).multiply(getBalance().getAmount()));
            setInterestAddDate(LocalDate.now());
        }
    }


/*Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12)
    then 1% interest will be added to the account monthly. When the balance of a credit card is accessed,
    check to determine if it has been 1 month or more since the account was created or since interested was added,
    and if so, add the appropriate interest to the balance.*/

}
