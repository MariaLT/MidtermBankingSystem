package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.models.users.User;
import com.ironhack.MidtermBankingSystem.repository.users.RoleRepository;
import com.ironhack.MidtermBankingSystem.repository.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoleControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private User admin, owner1;
    private Role adminRole, ownerRol;



    @BeforeEach
    void setUp() {
        admin = new User("admin", passwordEncoder.encode("password"));
        adminRole = new Role("ADMIN", admin);
        owner1 = new User("owner_1", "sandia");
        ownerRol = new Role("OWNER", owner1);
        admin.setRoles(Set.of(adminRole));
        userRepository.saveAll(List.of(admin, owner1));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createRol_test_valid() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic QURNSU46cGFzc3dvcmQ=");

        ownerRol.setId(0L);
        String body = objectMapper.writeValueAsString(ownerRol);

        MvcResult mvcResult = mockMvc.perform(
                        post("/roles")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("OWNER"));
        assertTrue(roleRepository.existsById(roleRepository.findRoleByUser(owner1).getId()));

    }
}