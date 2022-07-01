package com.ironhack.MidtermBankingSystem.service.impl.accounts;

import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.*;
import com.ironhack.MidtermBankingSystem.repository.accounts.*;
import com.ironhack.MidtermBankingSystem.service.interfaces.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public void createChekingsAccount(Account account) {
        boolean loop = true;
        while (loop){
                if (accountRepository.findAll().contains(account.getId())) {
                    account.setId(account.getId());
                } else{
                    loop = false;
                }
        }
        if (Period.between(account.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 25) {
            StudentChecking studentChecking = new StudentChecking(account.getId(), account.getBalance(),
                    account.getSecretKey(),
                    account.getPrimaryOwner(), account.getStatus(), account.getCreationDate());
            studentCheckingRepository.save(studentChecking);
        } else {
            Checking checking = new Checking(account.getId(), account.getBalance(), account.getSecretKey(),
                    account.getPrimaryOwner(),
                    account.getStatus(), account.getCreationDate());
            checkingRepository.save(checking);
        }

    }

    @Override
    public Saving createSavingAccount(Saving saving) {
        boolean loop = true;
        while (loop){
            if (accountRepository.findAll().contains(saving.getId())) {
                saving.setId(saving.getId());
            } else{
                loop = false;
            }
        }
        return saving;
    }

    @Override
    public CreditCard createCreditCardAccount(CreditCard creditCard) {
        boolean loop = true;
        while (loop){
            if (accountRepository.findAll().contains(creditCard.getId())) {
                creditCard.setId(creditCard.getId());
            } else{
                loop = false;
            }
        }
        return creditCard;
    }

    @Override
    public void updateStatusAccount(Long id, Status status) {
         Account account= accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));;
        if (account.getClass()==Saving.class){
            Saving saving = (Saving) account;
            saving.setStatus(status);
            savingRepository.save(saving);

        } else if (account.getClass()==CreditCard.class) {
            CreditCard creditCard = (CreditCard) account;
            creditCard.setStatus(status);
            creditCardRepository.save(creditCard);

        } else if (account.getClass()==Checking.class) {
            Checking checking = (Checking) account;
            checking.setStatus(status);
            checkingRepository.save(checking);

        } else if (account.getClass()==StudentChecking.class) {
            StudentChecking studentChecking = (StudentChecking) account;
            studentChecking.setStatus(status);
            studentCheckingRepository.save(studentChecking);

        }


    }
}
