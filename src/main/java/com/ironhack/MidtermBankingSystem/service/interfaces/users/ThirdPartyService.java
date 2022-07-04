package com.ironhack.MidtermBankingSystem.service.interfaces.users;

import com.ironhack.MidtermBankingSystem.controller.dto.ThirdPartyTransferDTO;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;

import java.math.BigDecimal;

public interface ThirdPartyService {
    ThirdParty createThirdParty(ThirdParty thirdParty);

    void thirdPartySendMoney(String hashedKey, BigDecimal amount, Long accountId, String secretKey);

    void thirdPartyReceiveMoney(String hashedKey, BigDecimal amount, Long accountId, String secretKey);

    void deleteThirdParty(Long id);

}

