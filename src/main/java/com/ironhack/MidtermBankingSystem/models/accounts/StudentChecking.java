package com.ironhack.MidtermBankingSystem.models.accounts;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account {
}
