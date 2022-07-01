package com.ironhack.MidtermBankingSystem.controller.impl.accounts;

import com.ironhack.MidtermBankingSystem.controller.interfaces.accounts.AccountController;
import com.ironhack.MidtermBankingSystem.models.accounts.*;
import com.ironhack.MidtermBankingSystem.repository.accounts.CheckingRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.CreditCardRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.SavingRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Saving createSavingAccount(@RequestBody @Valid Saving saving) {
        return savingRepository.save(saving);
    }

    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount(@RequestBody @Valid Account account) {
        if (accountService.createChekingsAccount(account).getClass() == Checking.class) {
            return checkingRepository.save((Checking) accountService.createChekingsAccount(account));
        }else{
            return studentCheckingRepository.save((StudentChecking) accountService.createChekingsAccount(account));
        }
    }

    @PostMapping("/creditcards")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createCreditCardAccount(@RequestBody @Valid CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }


}


