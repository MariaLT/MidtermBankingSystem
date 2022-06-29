package com.ironhack.MidtermBankingSystem.models.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Saving extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
    })
    private Money minimumBalance;

    private BigDecimal interestRate;

    /**
     * Minimum default value of interested rate:
     * 0.0025
     */
    private final static BigDecimal DEFAULT_MIN_VALUE_INTEREST_RATE = BigDecimal.valueOf(0.0025);


    /**
     * Maximum value of interested rate:
     * 0.50
     */
    private final static BigDecimal MAX_VALUE_INTEREST_RATE = BigDecimal.valueOf(0.50);


    // Saving accounts should have a default minimumBalance of 1000
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

    //Saving accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
    /**
     * Minimum value of balance:
     * 1000
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
    })
    private final static Money MIN_BALANCE = new Money(BigDecimal.valueOf(100));


    /**
     * Empty constructor
     */
    public Saving() {

    }

    /**
     * Constructor with one owner, specifying balance, secret key, primary owner and penalty fee,
     * status default as active and creation date default as current date. Saving accounts have
     * a default interest rate of 0.0025, however it may be instantiated with an interest rate other
     * than the default, with a maximum interest rate of 0.5
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param penaltyFee
     * @param minimumBalance
     * @param interestRate
     */
    public Saving(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee, Money minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, penaltyFee);
        this.minimumBalance = minimumBalance;
        setInterestRate(interestRate);
    }

    /**
     * Constructor with one owner, specifying balance, secret key, primary owner and penalty fee, status
     * and creation date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     * @param minimumBalance
     * @param interestRate
     */
    public Saving(Money balance, String secretKey, AccountHolder primaryOwner, BigDecimal penaltyFee, Status status, LocalDate creationDate, Money minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, penaltyFee, status, creationDate);
        this.minimumBalance = minimumBalance;
        setInterestRate(interestRate);
    }

    /**
     * Constructor with two owner, specifying balance, secret key, primary owner, secondary owner and penalty
     * fee, status default as active and creation date default as current date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param penaltyFee
     * @param minimumBalance
     * @param interestRate
     */
    public Saving(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, Money minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee);
        this.minimumBalance = minimumBalance;
        setInterestRate(interestRate);
    }

    /**
     * Constructor with two owner, specifying balance, secret key, primary owner, secondary owner and penalty
     * fee, status and creation date.
     *
     * @param balance
     * @param secretKey
     * @param primaryOwner
     * @param secondaryOwner
     * @param penaltyFee
     * @param status
     * @param creationDate
     * @param minimumBalance
     * @param interestRate
     */
    public Saving(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, Status status, LocalDate creationDate, Money minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, status, creationDate);
        this.minimumBalance = minimumBalance;
        setInterestRate(interestRate);
    }

    // Getter and Setter Minimum Balance
    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {

        if (minimumBalance == null) {
            this.minimumBalance = DEFAULT_MIN_BALANCE;        }
        else if (minimumBalance.getAmount().compareTo(MIN_BALANCE.getAmount()) == -1) {
            this.minimumBalance = DEFAULT_MIN_BALANCE;
        } else {
            this.minimumBalance = minimumBalance;
        }
    }

    // Getter and Setter Interest Rate
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(DEFAULT_MIN_VALUE_INTEREST_RATE) == -1) {
            this.interestRate = DEFAULT_MIN_VALUE_INTEREST_RATE;
        } else if (interestRate.compareTo(MAX_VALUE_INTEREST_RATE) == 1) {
            this.interestRate = MAX_VALUE_INTEREST_RATE;

        } else {
            this.interestRate = interestRate;
        }
    }


}
