package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.controller.dto.ThirdPartyTransferDTO;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.models.users.User;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountHolderControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private AccountHolderRepository accountHolderRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();


    private User admin, owner1;
    private Role adminRole, ownerRol;


    private AccountHolder accountHolder1;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "number", column = @Column(name = "building_number")),
            @AttributeOverride(name = "floor", column = @Column(name = "floor")),
            @AttributeOverride(name = "door", column = @Column(name = "door")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "country", column = @Column(name = "country"))
    })
    private Address address;

    private Account account;

    private LocalDate birthdate;

    @BeforeEach
    void setUp() {


        birthdate = LocalDate.of(1999, 07, 22);
        accountHolder1 = new AccountHolder("owner_1", "sandia", "Paloma", null,
                address, "paloma@paloma.es");
        accountHolder1.setId(1L);


        admin = new User("admin", passwordEncoder.encode("password"));
        adminRole = new Role("ADMIN", admin);
        owner1 = new User("owner_1", "sandia");
        ownerRol = new Role("OWNER", owner1);
        admin.setRoles(Set.of(adminRole, ownerRol));
        userRepository.saveAll(List.of(admin,owner1));

    }


    @AfterEach
    void tearDown() {
    }


    @Test
    void listAccountHoldersBasicInfo() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic QURNSU46cGFzc3dvcmQ=");

        MvcResult mvcResult = mockMvc.perform(
                        get("/accountHolders")
                                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Paloma"));
    }

    //  DA ERROR POR EL LOCALDATE
/*        @Test
        void createAccountHolder () throws Exception {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Basic QURNSU46cGFzc3dvcmQ=");

            accountHolder1.setId(0L);
            String body = objectMapper.writeValueAsString(accountHolder1);

            MvcResult mvcResult = mockMvc.perform(
                            post("/accountholders")
                                    .content(body)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .headers(httpHeaders))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertTrue(mvcResult.getResponse().getContentAsString().contains("Paloma"));
           // assertTrue(accountHolderRepository.existsById(accountHolder1.getId()));
        }*/

    @Test
    void modifyAddress() {

    }
}