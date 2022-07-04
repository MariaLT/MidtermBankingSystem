package com.ironhack.MidtermBankingSystem.controller.dto;

import com.ironhack.MidtermBankingSystem.auxiliary.Money;

public class TransferDTO {

    private Long id;

    private String secretKey;

    private Money transfer;

    private Long idAccountReceiveMoney;

    private String nameHolderAccountReceiveMoney;

    public TransferDTO(Long id, String secretKey, Money transfer,
                       Long idAccountReceiveMoney, String nameHolderAccountReceiveMoney) {
        this.id = id;
        this.secretKey = secretKey;
        this.transfer = transfer;
        this.idAccountReceiveMoney = idAccountReceiveMoney;
        this.nameHolderAccountReceiveMoney = nameHolderAccountReceiveMoney;
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

    public Money getTransfer() {
        return transfer;
    }

    public void setTransfer(Money transfer) {
        this.transfer = transfer;
    }

    public Long getIdAccountReceiveMoney() {
        return idAccountReceiveMoney;
    }

    public void setIdAccountReceiveMoney(Long idAccountReceiveMoney) {
        this.idAccountReceiveMoney = idAccountReceiveMoney;
    }

    public String getNameHolderAccountReceiveMoney() {
        return nameHolderAccountReceiveMoney;
    }

    public void setNameHolderAccountReceiveMoney(String nameHolderAccountReceiveMoney) {
        this.nameHolderAccountReceiveMoney = nameHolderAccountReceiveMoney;
    }
}
