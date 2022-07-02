package com.ironhack.MidtermBankingSystem.service.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.controller.dto.AccountBalanceDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountInfoToAdminDTO;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.*;

import java.util.Map;

public interface AccountService {

    void createChekingsAccount (Account account);

    Saving createSavingAccount(Saving saving);

    CreditCard createCreditCardAccount(CreditCard creditCard);

    void updateStatusAccount(Long id, Status status);

    void applyInterestSavingAccount();

    void applyInterestCreditCard();

    void monthlyMaintenanceFee();

    void penaltyFee();

    Map<Long,AccountInfoToAdminDTO> accountInfoToAdmin();

    AccountInfoToAdminDTO accountInfoToAdminById(Long id);

    void modifyBalanceByAdmin(Long id, AccountBalanceDTO accountBalanceDTO);
}
