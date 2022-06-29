package com.ironhack.MidtermBankingSystem.models.users;


import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;

import javax.persistence.*;
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
    @OneToOne // Como entidad o como clase
    private Address primaryAddress;
    private String mailingAddress; // regex

    @OneToMany(mappedBy = "primaryOwner")
    private Set<Account> primaryOwnerAccount;

    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> secundaryOwnerAccount;



}
