package com.ironhack.MidtermBankingSystem.models.users;


import javax.persistence.*;

/**
 * Users who receive and send money to other accounts.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends User {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    private String name;
    private String hashedKey;

}
