package com.ironhack.MidtermBankingSystem.service.impl.accounts;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountBalanceDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountInfoToAdminDTO;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.*;
import com.ironhack.MidtermBankingSystem.repository.accounts.*;
import com.ironhack.MidtermBankingSystem.service.interfaces.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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
        while (loop) {
            if (accountRepository.findAll().contains(account.getId())) {
                account.setId(account.getId());
            } else {
                loop = false;
            }
        }
        if (Period.between(account.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 25) {
            StudentChecking studentChecking = new StudentChecking(account.getId(), account.getBalance(),
                    account.getSecretKey(),
                    account.getPrimaryOwner(), account.getSecondaryOwner(), account.getStatus(), account.getCreationDate());
            studentCheckingRepository.save(studentChecking);
        } else {
            if (account.getBalance().getAmount().compareTo(Checking.MINIMUM_BALANCE) == -1) {
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Checking accounts should have " +
                        "a minimumBalance of 250");
            } else {
                Checking checking = new Checking(account.getId(), account.getBalance(), account.getSecretKey(),
                        account.getPrimaryOwner(), account.getSecondaryOwner(),
                        account.getStatus(), account.getCreationDate(), null);
                checkingRepository.save(checking);
            }
        }
    }

    @Override
    public Saving createSavingAccount(Saving saving) {
        boolean loop = true;
        while (loop) {
            if (accountRepository.findAll().contains(saving.getId())) {
                saving.setId(saving.getId());
            } else {
                loop = false;
            }
        }
        return saving;
    }

    @Override
    public CreditCard createCreditCardAccount(CreditCard creditCard) {
        boolean loop = true;
        while (loop) {
            if (accountRepository.findAll().contains(creditCard.getId())) {
                creditCard.setId(creditCard.getId());
            } else {
                loop = false;
            }
        }
        return creditCard;
    }

    @Override
    public void updateStatusAccount(Long id, Status status) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));
        ;
        if (account.getClass() == Saving.class) {
            Saving saving = (Saving) account;
            saving.setStatus(status);
            savingRepository.save(saving);

        } else if (account.getClass() == CreditCard.class) {
            CreditCard creditCard = (CreditCard) account;
            creditCard.setStatus(status);
            creditCardRepository.save(creditCard);

        } else if (account.getClass() == Checking.class) {
            Checking checking = (Checking) account;
            checking.setStatus(status);
            checkingRepository.save(checking);

        } else if (account.getClass() == StudentChecking.class) {
            StudentChecking studentChecking = (StudentChecking) account;
            studentChecking.setStatus(status);
            studentCheckingRepository.save(studentChecking);

        }
    }

    public void applyInterestSavingAccount() {
        List<Saving> savingList = savingRepository.findAll();
        for (Saving saving : savingList) {
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

    @Override
    public void applyInterestCreditCard() {
        List<CreditCard> creditCardList = creditCardRepository.findAll();
        for (CreditCard creditCard : creditCardList) {
            if (Period.between(creditCard.getCreationDate(), LocalDate.now()).getYears() >= 1
                    && creditCard.getInterestAddDate() == null) {
                BigDecimal years = BigDecimal.valueOf(Period.between(creditCard.getCreationDate(), LocalDate.now()).getYears());
                creditCard.setBalance(new Money(creditCard.getBalance().increaseAmount(
                        creditCard.getInterestRate().multiply(years).multiply(creditCard.getBalance().
                                getAmount()))));
                creditCard.setInterestAddDate(LocalDate.now());
                creditCardRepository.save(creditCard);
            } else if (Period.between(creditCard.getInterestAddDate(), LocalDate.now()).getYears() >= 1) {
                BigDecimal years = BigDecimal.valueOf(Period.between(creditCard.getInterestAddDate(), LocalDate.now()).getYears());
                creditCard.setBalance(new Money(creditCard.getBalance().increaseAmount(
                        creditCard.getInterestRate().multiply(years).multiply(creditCard.getBalance().
                                getAmount()))));
                creditCard.setInterestAddDate(LocalDate.now());
                creditCardRepository.save(creditCard);
            }

        }
    }

    @Override
    public void monthlyMaintenanceFee() {
        List<Checking> checkingList = checkingRepository.findAll();
        for (Checking checking : checkingList) {
            if (Period.between(checking.getCreationDate(), LocalDate.now()).getMonths() >= 1
                    && checking.getDateMaintenanceFee() == null) {
                BigDecimal months = BigDecimal.valueOf(Period.between(checking.getCreationDate(), LocalDate.now()).getMonths());
                checking.setBalance(new Money(checking.getBalance().decreaseAmount(Checking.
                        MONTHLY_MAINTENANCE_FEE.multiply(months))));
                checking.setDateMaintenanceFee(LocalDate.now());
                checkingRepository.save(checking);
            } else if (Period.between(checking.getDateMaintenanceFee(), LocalDate.now()).getMonths() >= 1) {
                BigDecimal months = BigDecimal.valueOf(Period.between(checking.getDateMaintenanceFee(), LocalDate.now()).getMonths());
                checking.setBalance(new Money(checking.getBalance().decreaseAmount(Checking.
                        MONTHLY_MAINTENANCE_FEE.multiply(months))));
                checking.setDateMaintenanceFee(LocalDate.now());
                checkingRepository.save(checking);
            }
        }
    }

    @Override
    public void penaltyFee() {
        List<Saving> savingList = savingRepository.findAll();
        List<Checking> checkingList = checkingRepository.findAll();
        for (Saving saving : savingList) {
            if (saving.getBalance().getAmount().compareTo(Saving.MIN_BALANCE) == -1) {
                saving.setBalance(new Money(saving.getBalance().
                        decreaseAmount(Saving.PENALTY_FEE)));
            }
        }
        for (Checking checking : checkingList) {
            if (checking.getBalance().getAmount().compareTo(Saving.MIN_BALANCE) == -1) {
                checking.setBalance(new Money(checking.getBalance().
                        decreaseAmount(Saving.PENALTY_FEE)));
            }
        }
    }

    @Override
    public Map<Long, AccountInfoToAdminDTO> accountInfoToAdmin() {
        List<Account> accountList = accountRepository.findAll();
        Map<Long, AccountInfoToAdminDTO> infoToAdminDTOMap = new HashMap<>();
        List<String> namesOwners = new ArrayList<>();
        for (Account account : accountList) {
            namesOwners.add(account.getPrimaryOwner().getName());
            namesOwners.add(account.getSecondaryOwner().getName());
            infoToAdminDTOMap.put(account.getId(), new AccountInfoToAdminDTO(namesOwners,
                    account.getBalance()));
        }
        return infoToAdminDTOMap;
    }

    @Override
    public AccountInfoToAdminDTO accountInfoToAdminById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));

        List<String> namesOwners = new ArrayList<>();
        namesOwners.add(account.getPrimaryOwner().getName());
        namesOwners.add(account.getSecondaryOwner().getName());

        AccountInfoToAdminDTO accountInfoToAdminDTO =
                new AccountInfoToAdminDTO(namesOwners, account.getBalance());

        return accountInfoToAdminDTO;
    }

    @Override
    public void modifyBalanceByAdmin(Long id, Money balance) {
        Account account = accountRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));

        account.setBalance(new Money(account.getBalance().getAmount().add(balance.
                getAmount())));

        accountRepository.save(account);

    }

    @Override
    public Money accountBalanceOwner(Long idOwner, Long idAccount, String secretKey) {
        Account account = accountRepository.findById(idAccount).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));

        if (account.getPrimaryOwner().getId() == idOwner || account.getSecondaryOwner().getId() == idOwner
                && account.getSecretKey().equals(secretKey)) {
            return account.getBalance();
        } else {
            new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Incorrect information");
            return null;
        }

    }

    @Override
    public void transfer(Long idOwner, Long idAccount, String secretKey, Money transfer,
                         Long idAccountReceiveMoney, String nameHolderAccountReceiveMoney) {
        Account account = accountRepository.findById(idAccount).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));

        Account accountReceive = accountRepository.findById(idAccountReceiveMoney).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The account isn't exists"));


        if (account.getPrimaryOwner().getId() == idOwner || account.getSecondaryOwner().getId() == idOwner
                && account.getSecretKey().equals(secretKey) &&
                accountReceive.getPrimaryOwner().getName().equals(nameHolderAccountReceiveMoney) ||
                accountReceive.getSecondaryOwner().getName().equals(nameHolderAccountReceiveMoney)
                        && account.getBalance().getAmount().compareTo(transfer.getAmount()) == 1 &&
                        transfer.getAmount().compareTo(BigDecimal.valueOf(0)) == 1) {

            accountReceive.setBalance(new Money(accountReceive.getBalance().increaseAmount(transfer)));
            account.setBalance(new Money(account.getBalance().decreaseAmount(transfer)));

        } else {
            new ResponseStatusException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Incorrect information" +
                    "or the account has not sufficient funds");
        }
        accountRepository.save(account);
        accountRepository.save(accountReceive);
    }
}
