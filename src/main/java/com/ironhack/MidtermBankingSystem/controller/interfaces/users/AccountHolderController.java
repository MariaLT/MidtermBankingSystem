package com.ironhack.MidtermBankingSystem.controller.interfaces.users;

import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import java.util.List;

public interface AccountHolderController {

    AccountHolder createAccountHolder( AccountHolder accountHolder);


    List<AccountHolder> listAccountHolders ();

}
