package com.ironhack.MidtermBankingSystem.controller.interfaces.users;

import com.ironhack.MidtermBankingSystem.controller.dto.ThirdPartyTransferDTO;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import org.springframework.web.bind.annotation.RequestBody;

public interface ThirdPartyController {
    ThirdParty createThirdParty(ThirdParty thirdParty);

    void thirdPartySendMoney(String hashedKey, ThirdPartyTransferDTO thirdPartyTransferDTO);

    void thirdPartyReceiveMoney(String hashedKey, ThirdPartyTransferDTO thirdPartyTransferDTO);

    void deleteThirdParty(Long id);
}
