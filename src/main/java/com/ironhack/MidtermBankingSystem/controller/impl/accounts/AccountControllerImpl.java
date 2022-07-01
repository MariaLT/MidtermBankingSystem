package com.ironhack.MidtermBankingSystem.controller.impl.accounts;

import com.ironhack.MidtermBankingSystem.controller.dto.AccountStatusDTO;
import com.ironhack.MidtermBankingSystem.controller.interfaces.accounts.AccountController;
import com.ironhack.MidtermBankingSystem.enums.Status;
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
}


