package com.ironhack.MidtermBankingSystem.service.impl.accounts;

import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.accounts.Checking;
import com.ironhack.MidtermBankingSystem.models.accounts.StudentChecking;
import com.ironhack.MidtermBankingSystem.repository.accounts.CheckingRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.StudentCheckingRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    CheckingRepository checkingRepository;

    @Override
    public Account createChekingsAccount(Account account) {
        Account accountCreated = null;
        if (Period.between(account.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 25) {
            accountCreated = (StudentChecking) account;
        } else {
            accountCreated = (Checking) account;
        }
        return accountCreated;
    }


}
