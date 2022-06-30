package com.ironhack.MidtermBankingSystem.repository.accounts;

import com.ironhack.MidtermBankingSystem.models.accounts.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingRepository extends JpaRepository <Saving, Long> {
}
