package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.ironhack.MidtermBankingSystem.controller.dto.AccountHolderBasicInfoDTO;
import com.ironhack.MidtermBankingSystem.controller.interfaces.users.AccountHolderController;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
import com.ironhack.MidtermBankingSystem.repository.users.RoleRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
@Autowired
    RoleRepository roleRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountHolderService accountHolderService;

    @GetMapping("/accountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolderBasicInfoDTO> listAccountHoldersBasicInfo() {
        return accountHolderService.accountHolderList();
    }

    @PostMapping("/accountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {

        return accountHolderRepository.save(
                accountHolderService.createAccountHolder(accountHolder));
    }



}
