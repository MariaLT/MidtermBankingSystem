package com.ironhack.MidtermBankingSystem.controller.dto;

import com.ironhack.MidtermBankingSystem.enums.Status;

public class AccountStatusDTO {

    private Status status;

    public AccountStatusDTO() {
    }

    public AccountStatusDTO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
