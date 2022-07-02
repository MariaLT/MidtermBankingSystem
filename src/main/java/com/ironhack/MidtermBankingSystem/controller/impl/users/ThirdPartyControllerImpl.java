package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.ironhack.MidtermBankingSystem.controller.interfaces.users.ThirdPartyController;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.repository.users.ThirPartyRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyControllerImpl implements ThirdPartyController {
    @Autowired
    ThirPartyRepository thirPartyRepository;
    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("thirdparties/")
    public ThirdParty createThirdParty(@RequestBody ThirdParty thirdParty) {
        return thirPartyRepository.save(thirdPartyService.createThirdParty(thirdParty));
    }
}
