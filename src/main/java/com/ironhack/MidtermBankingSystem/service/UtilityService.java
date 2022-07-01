package com.ironhack.MidtermBankingSystem.service;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.models.accounts.Saving;
import com.ironhack.MidtermBankingSystem.repository.accounts.AccountRepository;
import com.ironhack.MidtermBankingSystem.repository.accounts.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UtilityService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    SavingRepository savingRepository;


    public UtilityService() {
    }

    /**
     * @return
     */
    public Long randomId() {
        Long randomId = null;
        boolean loop = true;
        Random random = new Random();
        List<Long> listAccountId = new ArrayList<>();
        while (loop) {
            Long randomLong = random.nextLong(18);
            for (int i = 0; i < accountRepository.findAll().size(); i++) {
                listAccountId.add(accountRepository.findAll().get(i).getId());
                if (!listAccountId.contains(randomLong)) {
                    randomId = randomLong;
                    loop = false;
                }
            }
        }
        return randomId;
    }

    public void interest(Long id, SavingRepository savingRepository) {
        Saving saving = savingRepository.findById(id).get();
        if (Period.between(saving.getCreationDate(), LocalDate.now()).getYears() == 1) {
            saving.setBalance(new Money(saving.getBalance().increaseAmount(saving.getInterestRate().
                    multiply(saving.getBalance().
                    getAmount()))));
            saving.setInterestAddDate(LocalDate.now());
            savingRepository.save(saving);
        } else if (Period.between(saving.getInterestAddDate(), LocalDate.now()).getYears() == 1) {
            saving.setBalance(new Money(saving.getBalance().increaseAmount(saving.getInterestRate().
                    multiply(saving.getBalance().
                            getAmount()))));
            saving.setInterestAddDate(LocalDate.now());
            savingRepository.save(saving);
        }
    }
}
