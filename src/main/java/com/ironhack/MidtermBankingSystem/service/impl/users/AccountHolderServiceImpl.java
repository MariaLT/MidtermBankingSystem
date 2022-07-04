package com.ironhack.MidtermBankingSystem.service.impl.users;

import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountHolderBasicInfoDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AddressDTO;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.repository.accounts.AccountRepository;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
import com.ironhack.MidtermBankingSystem.repository.users.RoleRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    RoleRepository roleRepository;


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

            Role [] objectsArray = accountHolderList.get(i).getRoles().toArray(Role[]::new);
            ArrayList<String> rolesList = new ArrayList<>();
            for (int j = 0; j < objectsArray.length; j++) {
               rolesList.add(objectsArray[j].getName());
            }
            accountHolderBasicInfoDTO.setRoles(rolesList);
            accountHolderBasicInfoDTO.setId(accountHolderList.get(i).getId());
            accountHolderBasicInfoDTO.setName(accountHolderList.get(i).getName());
            accountHolderBasicInfoDTO.setDateOfBirth(accountHolderList.get(i).getDateOfBirth());
            accountHolderBasicInfoDTO.setPrimaryAddress(accountHolderList.get(i).getPrimaryAddress());
            accountHolderBasicInfoDTO.setMailingAddress(accountHolderList.get(i).getMailingAddress());
            accountHolderBasicInfoDTOList.add(accountHolderBasicInfoDTO);

        }

        return accountHolderBasicInfoDTOList;
    }


    @Override
    public void modifyAddress(Long id, Address address) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The account holder is not exists"));

        accountHolder.setPrimaryAddress(address);

        accountHolderRepository.save(accountHolder);

    }
}
