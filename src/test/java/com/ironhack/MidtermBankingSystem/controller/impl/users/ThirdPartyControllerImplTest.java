package com.ironhack.MidtermBankingSystem.controller.impl.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MidtermBankingSystem.auxiliary.Address;
import com.ironhack.MidtermBankingSystem.auxiliary.Money;
import com.ironhack.MidtermBankingSystem.controller.dto.ThirdPartyTransferDTO;
import com.ironhack.MidtermBankingSystem.enums.Status;
import com.ironhack.MidtermBankingSystem.models.accounts.Account;
import com.ironhack.MidtermBankingSystem.models.users.AccountHolder;
import com.ironhack.MidtermBankingSystem.models.users.Role;
import com.ironhack.MidtermBankingSystem.models.users.ThirdParty;
import com.ironhack.MidtermBankingSystem.models.users.User;
import com.ironhack.MidtermBankingSystem.repository.accounts.AccountRepository;
import com.ironhack.MidtermBankingSystem.repository.users.AccountHolderRepository;
import com.ironhack.MidtermBankingSystem.repository.users.ThirPartyRepository;
import com.ironhack.MidtermBankingSystem.repository.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.HttpClientErrorException;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ThirdPartyControllerImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThirPartyRepository thirPartyRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private User admin, owner1;
    private Role adminRole, ownerRol;

    private ThirdParty thirdParty1, thirdParty2;

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

    private ThirdPartyTransferDTO thirdPartyTransferDTO;

    @BeforeEach
    void setUp() {
        admin = new User("admin", passwordEncoder.encode("password"));
        adminRole = new Role("ADMIN", admin);
        owner1 = new User("OWNER_1", passwordEncoder.encode("sandia"));
        ownerRol = new Role("OWNER", owner1);
        admin.setRoles(Set.of(adminRole, ownerRol));
        userRepository.saveAll(List.of(admin, owner1));
        accountHolder1 = new AccountHolder("owner_1", "sandia", "Paloma", LocalDate.of(1999, 07, 22),
                address, "paloma@paloma.es");
        accountHolderRepository.save(accountHolder1);
        account = new Account(new Money(BigDecimal.valueOf(2000)), "sandia", accountHolder1, null, Status.ACTIVE, null);
        accountRepository.save(account);

    }


    @AfterEach
    void tearDown() {

        accountRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();

        thirPartyRepository.deleteAll();



    }

    @Test
    void createThirdParty_test_valid() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic QURNSU46cGFzc3dvcmQ=");
        thirdParty1 = new ThirdParty(1L, "Helados Porras", "heladosporras");
        String body = objectMapper.writeValueAsString(thirdParty1);

        MvcResult mvcResult = mockMvc.perform(
                        post("/thirdparties")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Helados Porras"));
        assertTrue(thirPartyRepository.existsById(thirdParty1.getId()));

    }

    @Test
    void thirdPartySendMoney_test_valid() throws Exception {

        thirdParty1 = new ThirdParty(1L, "Helados Porras", "heladosporras");
        thirPartyRepository.save(thirdParty1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic T1dORVJfMTpzYW5kaWE=");


        thirdPartyTransferDTO = new ThirdPartyTransferDTO(BigDecimal.valueOf(50), account.getId(), account.getSecretKey());
        String body = objectMapper.writeValueAsString(thirdPartyTransferDTO);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/thirdparties/" + thirdParty1.getHashedKey() + "/sendmoney")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();

        assertEquals(HttpStatus.NO_CONTENT, HttpStatus.resolve(mvcResult.getResponse().getStatus()));
        assertTrue(accountRepository.findById(account.getId()).get().getBalance().getAmount()
                .compareTo(account.getBalance().getAmount().add(thirdPartyTransferDTO.getAmount())) == 0);

    }

    @Test
    void thirdPartyReceiveMoney_test_valid() throws Exception {

        thirdParty1 = new ThirdParty(1L, "Helados Porras", "heladosporras");
        thirPartyRepository.save(thirdParty1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic T1dORVJfMTpzYW5kaWE=");


        thirdPartyTransferDTO = new ThirdPartyTransferDTO(BigDecimal.valueOf(50), account.getId(), account.getSecretKey());
        String body = objectMapper.writeValueAsString(thirdPartyTransferDTO);

        MvcResult mvcResult = mockMvc.perform(
                        patch("/thirdparties/" + thirdParty1.getHashedKey() + "/receivemoney")
                                .content(body)
                                .contentType(MediaType.APPLICATION_JSON)
                                .headers(httpHeaders))
                .andExpect(status().isNoContent())
                .andReturn();

        assertEquals(HttpStatus.NO_CONTENT, HttpStatus.resolve(mvcResult.getResponse().getStatus()));
        assertTrue(accountRepository.findById(account.getId()).get().getBalance().getAmount()
                .compareTo(account.getBalance().getAmount().subtract(thirdPartyTransferDTO.getAmount())) == 0);



    }

    @Test
    void deleteThirdParty_test_valid() throws Exception {

        thirdParty2 = new ThirdParty(1L, "Helados Porras", "heladosporras");
        thirPartyRepository.save(thirdParty2);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic QURNSU46cGFzc3dvcmQ=");


        MvcResult mvcResult = mockMvc.perform(
                        delete("/thirdparties/"+ thirdParty2.getId()+"/delete")
                                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(HttpStatus.OK, HttpStatus.resolve(mvcResult.getResponse().getStatus()));
        assertFalse(thirPartyRepository.existsById(thirdParty2.getId()));

    }
}