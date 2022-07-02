package com.ironhack.MidtermBankingSystem.controller.dto;

public class AccountIdAndSecretKeyDTO {

    private Long id;

    private String secretKey;

    public AccountIdAndSecretKeyDTO(Long id, String secretKey) {
        this.id = id;
        this.secretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
