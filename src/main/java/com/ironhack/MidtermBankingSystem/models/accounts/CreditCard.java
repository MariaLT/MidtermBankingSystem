package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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
     * CreditCard Account Constructor with one owner, specifying balance, secret key, primary owner and penalty fee,
     * status, creation date, credit limit and interest rate. If status is null, is assigned by default Status.ACTIVE.
     * If creation date is null, is assigned by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     * @param creditLimit
     * @param interestRate
     */
    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee, Status status,
                      LocalDate creationDate, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, penaltyFee, status, creationDate);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    /**
     * CreditCard Account Constructor with two owners, specifying balance, secret key, primary owner and penalty fee,
     * status, creation date, credit limit and interest rate. If status is null, is assigned by default Status.ACTIVE. If creation date is null,
     * is assigned by default the current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     * @param creditLimit
     * @param interestRate
     */
    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner,
                      BigDecimal penaltyFee, Status status, LocalDate creationDate, Money creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status, creationDate);
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

    // Getter and Setter interestRate
    public BigDecimal getInterestRate() {
        return interestRate;
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

}
