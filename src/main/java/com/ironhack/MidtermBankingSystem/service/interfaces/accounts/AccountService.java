package com.ironhack.MidtermBankingSystem.service.interfaces.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountBalanceDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountInfoToAdminDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.TransferDTO;
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

    void modifyBalanceByAdmin(Long id, Money balance);

    Money accountBalanceOwner(Long idOwner, Long idAccount, String secretKey);

    void transfer(Long idOwner, Long idAccount, String secretKey, Money transfer,
                  Long idAccountReceiveMoney, String nameHolderAccountReceiveMoney);

}
