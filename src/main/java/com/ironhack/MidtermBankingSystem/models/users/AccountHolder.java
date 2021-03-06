package com.ironhack.MidtermBankingSystem.models.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represent a user who is able to access their own accounts and only their accounts when passing
 * the correct credentials using Basic Auth.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{

    @Column(name = "first_name")
    private String name;

    @NotNull
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

    @Pattern(regexp = "^(.+)@(.+).[a-z]$", message = "Mail address not valid")
    private String mailingAddress; // regex

    @JsonIgnore
    @OneToMany(mappedBy = "primaryOwner")
    private Set<Account> primaryOwnerAccount;
    @JsonIgnore
    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> secundaryOwnerAccount;

    public AccountHolder() {
    }

    public AccountHolder(String username, String password, String name,
                         LocalDate dateOfBirth, Address primaryAddress, String mailingAddress) {
        super(username, password);
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

    public Set<Account> getPrimaryOwnerAccount() {
        return primaryOwnerAccount;
    }

    public void setPrimaryOwnerAccount(Set<Account> primaryOwnerAccount) {
        this.primaryOwnerAccount = primaryOwnerAccount;
    }

    public Set<Account> getSecundaryOwnerAccount() {
        return secundaryOwnerAccount;
    }

    public void setSecundaryOwnerAccount(Set<Account> secundaryOwnerAccount) {
        this.secundaryOwnerAccount = secundaryOwnerAccount;
    }
}
