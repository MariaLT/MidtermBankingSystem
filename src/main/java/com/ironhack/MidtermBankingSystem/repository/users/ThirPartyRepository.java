package com.ironhack.MidtermBankingSystem.repository.users;

import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirPartyRepository extends JpaRepository<ThirdParty, Long> {

    Optional<ThirdParty> findById(Long id);
    Optional<ThirdParty> findByHashedKey(String hashedKey);

}
