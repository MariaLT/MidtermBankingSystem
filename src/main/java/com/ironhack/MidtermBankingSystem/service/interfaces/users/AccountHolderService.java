package com.ironhack.MidtermBankingSystem.service.interfaces.users;

import com.ironhack.MidtermBankingSystem.controller.dto.AccountHolderBasicInfoDTO;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;

import java.util.List;

public interface AccountHolderService {

    AccountHolder createAccountHolder(AccountHolder accountHolder);

    List <AccountHolderBasicInfoDTO> accountHolderList ();

}
