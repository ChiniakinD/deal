package com.chiniakin.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class DealContractorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealContractorService dealContractorService;

    @BeforeEach
    void setUp() {
        String username = "user";
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("DEAL_SUPERUSER"),
                new SimpleGrantedAuthority("SUPERUSER")
        );
        UserDetails userDetails = new User(username, "password", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void saveContractorShouldSuccessfulSaved() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deal-contractor/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "id": "561b5244-29ed-4d18-9145-193775a1d0dc",
                                   "deal_id": "c3d5b726-92e8-4bb4-9826-66f72fce1c4d",
                                   "contractor_id": "1233",
                                   "name": "123",
                                   "main": "true"
                                 }
                                """
                        ))
                .andExpect(status().isOk());
    }

}