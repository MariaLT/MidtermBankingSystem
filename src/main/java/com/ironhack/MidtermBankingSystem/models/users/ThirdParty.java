package com.ironhack.MidtermBankingSystem.models.users;


import javax.persistence.*;
import java.util.Objects;

/**
 * Users who receive and send money to other accounts.
 */
@Entity
public class ThirdParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(Long id, String name, String hashedKey) {
        this.id=id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThirdParty that = (ThirdParty) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(hashedKey, that.hashedKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hashedKey);
    }
}
