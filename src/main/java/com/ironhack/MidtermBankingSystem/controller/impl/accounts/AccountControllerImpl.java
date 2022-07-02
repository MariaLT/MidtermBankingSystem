package com.ironhack.MidtermBankingSystem.controller.impl.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.controller.dto.*;
import com.ironhack.MidtermBankingSystem.controller.interfaces.accounts.AccountController;
import com.ironhack.MidtermBankingSystem.models.accounts.*;
import com.ironhack.MidtermBankingSystem.repository.accounts.CheckingRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.SavingRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class AccountControllerImpl implements AccountController {
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountService accountService;

    /**
     * Saving account creation.
     * @param saving
     * @return
     */
    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Saving createSavingAccount(@RequestBody @Valid Saving saving) {

        return savingRepository.save(accountService.createSavingAccount(saving));
    }

    /**
     * Checking account creation, if the primaryOwner is less than 24, a StudentChecking account will be created
     * otherwise a regular Checking Account will be created.
     * @param account
     */
    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCheckingAccount(@RequestBody @Valid Account account) {
        accountService.createChekingsAccount(account);
    }

    /**
     * Credit card creation.
     * @param creditCard
     * @return
     */
    @PostMapping("/creditcards")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCard creditCard) {
        return creditCardRepository.save(accountService.createCreditCardAccount(creditCard));
    }


    @PatchMapping("/accounts/{id}/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusAccount(@PathVariable Long id,
                                    @RequestBody @Valid AccountStatusDTO accountStatusDTO){
        accountService.updateStatusAccount(id, accountStatusDTO.getStatus());

    }

    @PatchMapping("/accounts/savinginterest")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void applyInterestSavingAccount() {
        accountService.applyInterestSavingAccount();
    }

    @PatchMapping("/accounts/creditcardinterest")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void applyInterestCreditCard() {
        accountService.applyInterestCreditCard();
    }


    @PatchMapping("/accounts/maintenance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void monthlyMaintenanceFee(){
        accountService.monthlyMaintenanceFee();
    }
    @PatchMapping("/accounts/penaltyfee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void penaltyFee(){
        accountService.penaltyFee();
    }

    @PatchMapping("/accounts/{id}/modifybalance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyBalanceByAdmin(@PathVariable Long id,
                                     @RequestBody @Valid AccountBalanceDTO accountBalanceDTO) {

        accountService.modifyBalanceByAdmin(id,accountBalanceDTO.getBalance());
    }

    @GetMapping("/accounts{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountInfoToAdminDTO accountInfoToAdminById(@PathVariable Long id) {
        return accountService.accountInfoToAdminById(id);
    }


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long,AccountInfoToAdminDTO> accountInfoToAdmin() {
        return accountService.accountInfoToAdmin();
    }

    @GetMapping("/accounts/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Money accountBalanceOwner(@PathVariable Long idOwner,
                                     @RequestBody @Valid AccountIdAndSecretKeyDTO accountIdAndSecretKeyDTO) {
        return accountService.accountBalanceOwner(idOwner, accountIdAndSecretKeyDTO.getId(),
                accountIdAndSecretKeyDTO.getSecretKey());
    }

    @PatchMapping("/accounts/{id}/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@PathVariable Long id,
                         @RequestBody @Valid TransferDTO transferDTO) {
        accountService.transfer(id, transferDTO.getId(), transferDTO.getSecretKey(), transferDTO.getTransfer(),
                transferDTO.getIdAccountReceiveMoney(), transferDTO.getNameHolderAccountReceiveMoney());

    }
}




