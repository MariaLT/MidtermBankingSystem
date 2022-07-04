package com.ironhack.MidtermBankingSystem.controller.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.controller.dto.*;
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

    Money accountBalanceOwner(Long idOwner, AccountIdAndSecretKeyDTO accountIdAndSecretKeyDTOy);

    void transfer(Long id, TransferDTO transferDTO);
}
