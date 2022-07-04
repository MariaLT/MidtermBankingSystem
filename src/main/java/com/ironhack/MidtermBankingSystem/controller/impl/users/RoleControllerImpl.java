package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.ironhack.MidtermBankingSystem.controller.interfaces.users.RoleController;
import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.User;
import com.ironhack.MidtermBankingSystem.repository.users.RoleRepository;
import com.ironhack.MidtermBankingSystem.repository.users.UserRepository;
import com.ironhack.MidtermBankingSystem.service.interfaces.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RoleControllerImpl implements RoleController {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRol(@RequestBody @Valid Role role) {
        return roleRepository.save(roleService.createRole(role));
    }
}
