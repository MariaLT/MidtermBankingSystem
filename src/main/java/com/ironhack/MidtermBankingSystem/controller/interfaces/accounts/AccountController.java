package com.ironhack.MidtermBankingSystem.controller.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.controller.dto.AccountBalanceDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountInfoToAdminDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountStatusDTO;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.accounts.CreditCard;
import com.ironhack.MidtermBankingSystem.models.accounts.Saving;

import java.util.List;
import java.util.Map;

public interface AccountController {

    Saving createSavingAccount(Saving saving);

    void createCheckingAccount(Account account);

    CreditCard createCreditCardAccount(CreditCard creditCard);

    void updateStatusAccount(Long id, AccountStatusDTO accountStatusDTO);

    void applyInterestSavingAccount();

    void applyInterestCreditCard();

    void monthlyMaintenanceFee();

    void penaltyFee();

    void modifyBalanceByAdmin(Long id, AccountBalanceDTO accountBalanceDTO);

    Map<Long, AccountInfoToAdminDTO> accountInfoToAdmin();

    AccountInfoToAdminDTO accountInfoToAdminById(Long id);
}
