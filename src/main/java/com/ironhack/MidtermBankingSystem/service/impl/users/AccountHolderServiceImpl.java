package com.ironhack.MidtermBankingSystem.service.impl.users;

import com.ironhack.MidtermBankingSystem.controller.dto.AccountHolderBasicInfoDTO;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.repository.accounts.AccountRepository;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Override
    public AccountHolder createAccountHolder(AccountHolder accountHolder) {
        Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(accountHolder.getId());
        if (optionalAccountHolder.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The account holder already exists");
        } else {
            return accountHolderRepository.save(accountHolder);
        }
    }

    @Override
    public List<AccountHolderBasicInfoDTO> accountHolderList() {
        List<AccountHolder> accountHolderList = accountHolderRepository.findAll();
        List<AccountHolderBasicInfoDTO> accountHolderBasicInfoDTOList = new ArrayList<>();

        AccountHolderBasicInfoDTO accountHolderBasicInfoDTO = new AccountHolderBasicInfoDTO();

        for (int i = 0; i < accountHolderList.size(); i++) {

            accountHolderBasicInfoDTO.setName(accountHolderList.get(i).getName());
            accountHolderBasicInfoDTO.setDateOfBirth(accountHolderList.get(i).getDateOfBirth());
            accountHolderBasicInfoDTO.setPrimaryAddress(accountHolderList.get(i).getPrimaryAddress());
            accountHolderBasicInfoDTO.setMailingAddress(accountHolderList.get(i).getMailingAddress());

            accountHolderBasicInfoDTOList.add(accountHolderBasicInfoDTO);

        }

        return accountHolderBasicInfoDTOList;
    }
}
