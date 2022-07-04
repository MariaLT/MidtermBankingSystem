package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.ironhack.MidtermBankingSystem.controller.dto.ThirdPartyTransferDTO;
import com.ironhack.MidtermBankingSystem.controller.interfaces.users.ThirdPartyController;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.repository.users.ThirPartyRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ThirdPartyControllerImpl implements ThirdPartyController {
    @Autowired
    ThirPartyRepository thirPartyRepository;
    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/thirdparties")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody @Valid ThirdParty thirdParty) {
        return thirPartyRepository.save(
                thirdPartyService.createThirdParty(thirdParty));
    }

    @PatchMapping("/thirdparties/{hashedKey}/sendmoney")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void thirdPartySendMoney(@PathVariable String hashedKey,
                                    @RequestBody @Valid ThirdPartyTransferDTO thirdPartyTransferDTO) {
        thirdPartyService.thirdPartySendMoney(hashedKey, thirdPartyTransferDTO.getAmount(),
                thirdPartyTransferDTO.getAccountId(), thirdPartyTransferDTO.getSecretKey());

    }

    @PatchMapping("/thirdparties/{hashedKey}/receivemoney")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void thirdPartyReceiveMoney(@PathVariable String hashedKey,
                                       @RequestBody @Valid ThirdPartyTransferDTO thirdPartyTransferDTO) {
        thirdPartyService.thirdPartyReceiveMoney(hashedKey,thirdPartyTransferDTO.getAmount(),
                thirdPartyTransferDTO.getAccountId(), thirdPartyTransferDTO.getSecretKey());

    }

    @DeleteMapping("/thirdparties/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteThirdParty(@PathVariable Long id) {
        thirdPartyService.deleteThirdParty(id);

    }
}
