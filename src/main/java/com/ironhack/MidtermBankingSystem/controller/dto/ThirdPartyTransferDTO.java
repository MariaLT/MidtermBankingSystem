package com.ironhack.MidtermBankingSystem.controller.dto;

import java.math.BigDecimal;

public class ThirdPartyTransferDTO {

    private BigDecimal amount;

    private Long accountId;

    private String secretKey;

    public ThirdPartyTransferDTO(BigDecimal amount, Long accountId, String secretKey) {
        this.amount = amount;
        this.accountId = accountId;
        this.secretKey = secretKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
