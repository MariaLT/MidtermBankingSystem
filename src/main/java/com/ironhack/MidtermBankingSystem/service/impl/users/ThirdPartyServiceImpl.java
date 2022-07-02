package com.ironhack.MidtermBankingSystem.service.impl.users;

import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.repository.users.ThirPartyRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    ThirPartyRepository thirPartyRepository;

    @Override
    public ThirdParty createThirdParty(ThirdParty thirdParty) {
        ThirdParty thirdPartyReturn = thirPartyRepository.findById(thirdParty.getId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The thirdParty already exists" ));
        return thirdPartyReturn;
    }
}
