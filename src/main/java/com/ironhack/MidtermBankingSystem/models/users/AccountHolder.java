package com.ironhack.MidtermBankingSystem.models.users;


import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represent a user who is able to access their own accounts and only their accounts when passing
 * the correct credentials using Basic Auth.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "first_name")
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
    private String mailingAddress; // regex

    @OneToMany(mappedBy = "primaryOwner")
    private Set<Account> primaryOwnerAccount;

    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> secundaryOwnerAccount;



}
