package com.ironhack.MidtermBankingSystem.models.users;


import javax.persistence.*;

/**
 * Users who receive and send money to other accounts.
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String name, String hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
