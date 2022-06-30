package com.ironhack.MidtermBankingSystem.controller.impl.accounts;

import com.ironhack.MidtermBankingSystem.controller.interfaces.accounts.SavingController;
import com.ironhack.MidtermBankingSystem.models.accounts.Saving;
import com.ironhack.MidtermBankingSystem.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class SavingControllerImpl implements SavingController {


    @Autowired
    SavingRepository savingRepository;

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Saving createSavingAccount(@RequestBody @Valid Saving saving) {
        return savingRepository.save(saving);
    }
}
