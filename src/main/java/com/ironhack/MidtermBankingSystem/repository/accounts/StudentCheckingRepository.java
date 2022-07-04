package com.ironhack.MidtermBankingSystem.repository.accounts;

import com.ironhack.MidtermBankingSystem.models.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository <StudentChecking, Long> {
}
