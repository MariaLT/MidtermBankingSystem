package com.ironhack.MidtermBankingSystem.service.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.*;

public interface AccountService {

    void createChekingsAccount (Account account);

    Saving createSavingAccount(Saving saving);

    CreditCard createCreditCardAccount(CreditCard creditCard);

    void updateStatusAccount(Long id, Status status);
}
