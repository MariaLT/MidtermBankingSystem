package com.ironhack.MidtermBankingSystem.service.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.*;
import com.ironhack.MidtermBankingSystem.repository.accounts.SavingRepository;

public interface AccountService {

    void createChekingsAccount (Account account);

    Saving createSavingAccount(Saving saving);

    CreditCard createCreditCardAccount(CreditCard creditCard);

    void updateStatusAccount(Long id, Status status);

    void applyInterestSavingAccount();

    void applyInterestCreditCard();
}
