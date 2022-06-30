package com.ironhack.MidtermBankingSystem.service;

import com.ironhack.MidtermBankingSystem.repository.accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UtilityService {

    @Autowired
    AccountRepository accountRepository;


    public UtilityService() {
    }

    /**
     *
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

}
