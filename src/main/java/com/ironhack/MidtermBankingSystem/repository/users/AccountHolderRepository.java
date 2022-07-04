package com.ironhack.MidtermBankingSystem.repository.users;


import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository <AccountHolder, Long> {

    // No hace falta, verdad¿?
    Optional<AccountHolder> findById(Long id);

}
