package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.service.interfaces.DealContractorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class ContractorToRoleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DealContractorService dealContractorService;

    @BeforeEach
    void setUp() {
        String username = "user";
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("DEAL_SUPERUSER"),
                new SimpleGrantedAuthority("USER")
        );
        UserDetails userDetails = new User(username, "password", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void addContractorToRoleShouldBeSuccessfulAdded() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/contractor-to-role/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                          "id": "8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec",
                                          "role_id": {
                                              "id": "BORROWER",
                                              "name": "Заемщик",
                                              "category": "BORROWER",
                                              "isActive": true
                                          }
                                      }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void deleteContractorToRoleShouldBeSuccessfulDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contractor-to-role/delete/{id}", "6e6a086f-5e74-49b4-b82b-99135aa4b555"))
                .andExpect(status().isOk());
    }
}