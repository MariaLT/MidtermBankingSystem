package com.ironhack.MidtermBankingSystem.repository.users;

import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirPartyRepository extends JpaRepository<ThirdParty, Long> {
}
