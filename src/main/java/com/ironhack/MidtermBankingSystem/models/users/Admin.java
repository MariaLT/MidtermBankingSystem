package com.ironhack.MidtermBankingSystem.models.users;

import javax.persistence.*;

/**
 * Represent a user who is admin and can create new accounts (Checking, Savings, or CreditCard Accounts) and
 * should be able to access the balance for any account and to modify it.
 */
// No pueden crear de estudaints¿?

@Entity
@Table(name = "admin_table")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    private String name;
}
