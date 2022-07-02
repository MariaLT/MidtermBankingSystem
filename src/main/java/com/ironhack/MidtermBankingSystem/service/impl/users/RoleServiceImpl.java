package com.ironhack.MidtermBankingSystem.service.impl.users;

import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.repository.users.RoleRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        if (optionalRole.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The role already exists");
        } else {
            return roleRepository.save(role);
        }
    }
}
