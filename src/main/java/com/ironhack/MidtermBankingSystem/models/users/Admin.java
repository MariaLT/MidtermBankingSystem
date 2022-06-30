package com.ironhack.MidtermBankingSystem.models.users;

import javax.persistence.*;
import java.util.Set;

/**
 * Represent a user who is admin and can create new accounts (Checking, Saving, or CreditCard Accounts) and
 * should be able to access the balance for any account and to modify it.
 */
// No pueden crear de estudaints¿?

@Entity
@Table(name = "admin_table")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    private String name;

    public Admin() {

    }

    public Admin(Long id, String username, String password, String name) {
        super(id, username, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // get -- > Admins should be able to access the balance for any account and to modify it.
}
