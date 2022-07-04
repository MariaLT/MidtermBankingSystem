package com.ironhack.MidtermBankingSystem.service.interfaces.users;

import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.controller.dto.AccountHolderBasicInfoDTO;
import com.ironhack.MidtermBankingSystem.controller.dto.AddressDTO;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface AccountHolderService {

    AccountHolder createAccountHolder(AccountHolder accountHolder);

    List <AccountHolderBasicInfoDTO> accountHolderList ();

    void modifyAddress(Long id, Address address);

}
