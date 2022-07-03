package com.ironhack.MidtermBankingSystem.service.impl.users;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.repository.accounts.AccountRepository;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
import com.ironhack.MidtermBankingSystem.repository.users.ThirPartyRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    ThirPartyRepository thirPartyRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public ThirdParty createThirdParty(ThirdParty thirdParty) {
        Optional<ThirdParty> optionalAccountHolder = thirPartyRepository.findById(thirdParty.getId());
        if (optionalAccountHolder.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The third party already exists");
        } else {
            return thirPartyRepository.save(thirdParty);
        }
    }

    @Override
    public void thirdPartySendMoney(String hashedKey, BigDecimal amount, Long accountId, String secretKey) {
        ThirdParty thirdPartyReturn = thirPartyRepository.findByHashedKey(hashedKey).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The thirdParty is not exists"));
        Account account = accountRepository.findById(accountId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The account is not exists"));

        if (account.getSecretKey().equals(secretKey) && amount.compareTo(BigDecimal.valueOf(0))==1){
            account.setBalance(new Money(account.getBalance().increaseAmount(amount)));
            accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Incorrect secret key" +
                    " or the amount is negative");
        }



    }

    @Override
    public void thirdPartyReceiveMoney(String hashedKey, BigDecimal amount, Long accountId, String secretKey) {
        ThirdParty thirdPartyReturn = thirPartyRepository.findByHashedKey(hashedKey).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The thirdParty is not exists"));
        Account account = accountRepository.findById(accountId).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The account is not exists"));

        if (account.getSecretKey().equals(secretKey)){
            account.setBalance(new Money(account.getBalance().decreaseAmount(amount)));
            accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Incorrect secret key" +
                    " or the amount is positive");
        }
    }

    @Override
    public void deleteThirdParty(Long id) {
        ThirdParty thirdPartyReturn = thirPartyRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The thirdParty is not exists"));

        thirPartyRepository.delete(thirdPartyReturn);

    }
}
