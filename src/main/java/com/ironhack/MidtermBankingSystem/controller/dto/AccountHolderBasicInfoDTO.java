package com.ironhack.MidtermBankingSystem.controller.dto;

import com.ironhack.MidtermBankingSystem.auxiliary.Address;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class AccountHolderBasicInfoDTO {

    private String name;
    private LocalDate dateOfBirth;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "number", column = @Column(name = "building_number")),
            @AttributeOverride(name = "floor", column = @Column(name = "floor")),
            @AttributeOverride(name = "door", column = @Column(name = "door")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private Address primaryAddress;

    @Pattern(regexp = "^(.+)@(.+)$", message = "Mail address not valid")
    private String mailingAddress; // regex


    public AccountHolderBasicInfoDTO() {
    }

    public AccountHolderBasicInfoDTO(String name, LocalDate dateOfBirth, Address primaryAddress, String mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
