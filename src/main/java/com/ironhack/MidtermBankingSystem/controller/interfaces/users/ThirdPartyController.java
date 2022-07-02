package com.ironhack.MidtermBankingSystem.controller.interfaces.users;

import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import org.springframework.web.bind.annotation.RequestBody;

public interface ThirdPartyController {
    ThirdParty createThirdParty(@RequestBody ThirdParty thirdParty);
}
