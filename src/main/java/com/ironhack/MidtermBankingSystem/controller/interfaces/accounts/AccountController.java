package com.ironhack.MidtermBankingSystem.controller.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.accounts.Checking;
import com.ironhack.MidtermBankingSystem.models.accounts.CreditCard;
import com.ironhack.MidtermBankingSystem.models.accounts.Saving;

public interface AccountController {

    Saving createSavingAccount(Saving saving);

    void createCheckingAccount(Account account);

    CreditCard createCreditCardAccount(CreditCard creditCard);

}
