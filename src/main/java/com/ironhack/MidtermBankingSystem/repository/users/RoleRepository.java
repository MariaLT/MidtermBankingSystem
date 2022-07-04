package com.ironhack.MidtermBankingSystem.repository.users;

import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByUser(User user);

}
